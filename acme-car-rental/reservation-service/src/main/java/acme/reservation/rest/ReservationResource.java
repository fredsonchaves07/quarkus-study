package acme.reservation.rest;

import acme.reservation.inventory.Car;
import acme.reservation.inventory.GraphQLInventoryClient;
import acme.reservation.inventory.InventoryClient;
import acme.reservation.rental.RentalClient;
import acme.reservation.reservation.entity.Reservation;
import acme.reservation.reservation.ReservationRepository;
import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
import java.util.stream.Collectors;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final InventoryClient inventoryClient;

    private final RentalClient rentalClient;

    @Inject
    SecurityContext context;

    public ReservationResource(
            @GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
            @RestClient RentalClient rentalClient
    ) {
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @GET
    @Path("availability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Car> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate) {
        List<Car> availableCars = inventoryClient.allCars();
        Map<Long, Car> carsById = new HashMap<>();
        for (Car car : availableCars)
            carsById.put(car.getId(), car);
        List<Reservation> reservations = Reservation.listAll();
        for (Reservation reservation : reservations)
            if (reservation.isReserved(startDate, endDate))
                carsById.remove(reservation.carId);
        return carsById.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Reservation make(Reservation reservation) {
        reservation.userId = context.getUserPrincipal() != null ?
                context.getUserPrincipal().getName() :
                "anonymous";
        reservation.persist();
        Log.info("Successfully reserved reservation " + reservation);
        if (reservation.startDay.equals(LocalDate.now())) {
            rentalClient.start(reservation.userId, reservation.id);
        }
        return reservation;
    }

    @GET
    @Path("all")
    public Collection<Reservation> allReservations() {
        String userId = context.getUserPrincipal() != null ?
                context.getUserPrincipal().getName() : null;
        return Reservation.<Reservation>streamAll()
                .filter(reservation -> userId == null || userId.equals(reservation.userId)).toList();
    }
}
