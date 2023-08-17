package acme.reservation.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;
import acme.reservation.rental.RentalClient;
import acme.reservation.inventory.Car;
import acme.reservation.inventory.InventoryClient;
import acme.reservation.reservation.Reservation;
import acme.reservation.reservation.ReservationRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.util.*;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final ReservationRepository repository;

    private final InventoryClient client;

    private final RentalClient rentalClient;

    public ReservationResource(ReservationRepository repository, InventoryClient client, @RestClient RentalClient rentalClient) {
        this.repository = repository;
        this.client = client;
        this.rentalClient = rentalClient;
    }

    @GET
    @Path("availability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Car> availability(@RestQuery LocalDate starDate, @RestQuery LocalDate endDate) {
        List<Car> availableCars = client.allCars();
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
