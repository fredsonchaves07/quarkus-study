package acme.reservation.reservation;

import acme.reservation.reservation.entity.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();

    Reservation save(Reservation reservation);
}
