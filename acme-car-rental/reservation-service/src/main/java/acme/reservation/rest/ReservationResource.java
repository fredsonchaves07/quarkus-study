package acme.reservation.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
    public Reservation make(Reservation reservation) {
        return repository.save(reservation);
    }
}
