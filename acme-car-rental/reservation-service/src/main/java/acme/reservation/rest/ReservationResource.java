package acme.reservation.rest;

import acme.reservation.inventory.Car;
import acme.reservation.inventory.GraphQLInventoryClient;
import acme.reservation.inventory.InventoryClient;
import acme.reservation.rental.RentalClient;
import acme.reservation.reservation.entity.Reservation;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final InventoryClient inventoryClient;
    private final RentalClient rentalClient;

    @Inject
    SecurityContext context;

    public ReservationResource(
            @GraphQLClient("inventory")
            GraphQLInventoryClient inventoryClient,
            @RestClient RentalClient rentalClient) {
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @WithTransaction
    public Uni<Reservation> make(Reservation reservation) {
        reservation.userId = context.getUserPrincipal() != null ?
                context.getUserPrincipal().getName() : "anonymous";
        return reservation.<Reservation>persist().onItem()
                .call(persistedReservation -> {
                    Log.info("Successfully reserved reservation " + persistedReservation);
                    if (persistedReservation.startDay.equals(LocalDate.now())) {
                        return rentalClient.start(persistedReservation.userId,
                                        persistedReservation.id)
                                .onItem().invoke(rental ->
                                        Log.info("Successfully started rental " + rental))
                                .replaceWith(persistedReservation);

                    }
                    return Uni.createFrom().item(persistedReservation);
                });
    }

    @GET
    @Path("all")
    public Uni<List<Reservation>> allReservations() {
        String userId = context.getUserPrincipal() != null ?
                context.getUserPrincipal().getName() : null;
        return Reservation.<Reservation>listAll()
                .onItem().transform(reservations -> reservations.stream()
                        .filter(reservation -> userId == null ||
                                userId.equals(reservation.userId))
                        .collect(Collectors.toList()));
    }

    @GET
    @Path("availability")
    public Uni<Collection<Car>> availability(@RestQuery LocalDate startDate,
                                             @RestQuery LocalDate endDate) {
        // obtain all cars from inventory
        Uni<Map<Long, Car>> carsUni = inventoryClient.allCars()
                .map(cars -> cars.stream().collect(Collectors
                        .toMap(car -> car.id, Function.identity())));
        Uni<List<Reservation>> reservationsUni = Reservation.listAll();
        return Uni.combine().all()
                .unis(carsUni, reservationsUni)
                .asTuple()
                .chain(tuple -> {
                    Map<Long, Car> carsById = tuple.getItem1();
                    List<Reservation> reservations = tuple.getItem2();
// for each reservation, remove the car from the map
                    for (Reservation reservation : reservations) {
                        if (reservation.isReserved(startDate, endDate)) {
                            carsById.remove(reservation.carId);
                        }
                    }
                    return Uni.createFrom().item(carsById.values());
                });
    }
}
