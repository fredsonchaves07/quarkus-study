package acme.reservation.rest;

import acme.reservation.inventory.Car;
import acme.reservation.inventory.InventoryClient;
import acme.reservation.rental.RentalClient;
import acme.reservation.reservation.Reservation;
import acme.reservation.reservation.ReservationRepository;
import io.smallrye.graphql.client.GraphQLClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final ReservationRepository repository;

    private final InventoryClient inventoryClient;

    private final RentalClient rentalClient;

    public ReservationResource(
            ReservationRepository repository,
            @GraphQLClient("inventory") InventoryClient inventoryClient,
            @RestClient RentalClient rentalClient
    ) {
        this.repository = repository;
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @GET
    @Path("availability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Car> availability(@RestQuery LocalDate starDate, @RestQuery LocalDate endDate) {
        List<Car> availableCars = inventoryClient.allCars();
        Map<Long, Car> carsById = new HashMap<>();
        for (Car car : availableCars)
            carsById.put(car.getId(), car);
        List<Reservation> reservations = repository.findAll();
        for (Reservation reservation : reservations)
            if (reservation.isReserved(starDate, endDate))
                carsById.remove(reservation.carId);
        return carsById.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation make(Reservation reservation) {
        Reservation result = repository.save(reservation);
        String userId = "x";
        if (reservation.startDay.equals(LocalDate.now())) {
            rentalClient.start(userId, result.id);
        }
        return result;
    }


}
