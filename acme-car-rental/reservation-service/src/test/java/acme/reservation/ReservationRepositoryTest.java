package acme.reservation;

import acme.reservation.reservation.Reservation;
import acme.reservation.reservation.ReservationRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.wildfly.common.Assert.assertNotNull;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class ReservationRepositoryTest {

    @Inject
    ReservationRepository repository;

    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation();
        reservation.startDay = LocalDate.now().plus(5, ChronoUnit.DAYS);
        reservation.endDay = LocalDate.now().plus(12, ChronoUnit.DAYS);
        reservation.carId = 384L;
        repository.save(reservation);
        assertNotNull(reservation.id);
        assertTrue(repository.findAll().contains(reservation));
    }
}
