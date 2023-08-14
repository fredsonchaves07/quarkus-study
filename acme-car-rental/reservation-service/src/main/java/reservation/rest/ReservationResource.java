package reservation.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;
import reservation.inventory.Car;
import reservation.inventory.InventoryClient;
import reservation.reservation.Reservation;
import reservation.reservation.ReservationRepository;

import java.time.LocalDate;
import java.util.*;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final ReservationRepository repository;

    private final InventoryClient client;

    public ReservationResource(ReservationRepository repository, InventoryClient client) {
        this.repository = repository;
        this.client = client;
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
