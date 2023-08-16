package acme.reservation.reservation;

import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class InMemoryReservationsRepository implements ReservationRepository {

    private final AtomicLong ids = new AtomicLong(0);

    private final List<Reservation> strore = new CopyOnWriteArrayList<>();

    @Override
    public List<Reservation> findAll() {
        return Collections.unmodifiableList(strore);
    }

    @Override
    public Reservation save(Reservation reservation) {
        reservation.id = ids.incrementAndGet();
        strore.add(reservation);
        return reservation;
    }
}
