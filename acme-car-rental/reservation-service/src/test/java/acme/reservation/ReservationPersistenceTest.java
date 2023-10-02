package acme.reservation;

import acme.reservation.reservation.ReservationRepository;
import acme.reservation.reservation.entity.Reservation;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.UniAsserter;
import io.quarkus.test.vertx.UniAsserterInterceptor;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.vertx.RunOnVertxContext;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

@QuarkusTest
public class ReservationPersistenceTest {

    // TODO replace with TransactionalUniAsserter
    @Test
    @RunOnVertxContext
    public void testCreateReservation(UniAsserter uniAsserter) {
        final UniAsserter asserter = new UniAsserterInterceptor(uniAsserter) {
            @Override
            protected <T> Supplier<Uni<T>> transformUni(Supplier<Uni<T>> uniSupplier) {
                return () -> Panache.withTransaction(uniSupplier);
            }
        };

        Reservation reservation = new Reservation();
        reservation.startDay = LocalDate.now().plusDays(5);
        reservation.endDay = LocalDate.now().plusDays(12);
        reservation.carId = 384L;

        asserter.<Reservation>assertThat(() -> reservation.persist(),
                r -> {
                    Assertions.assertNotNull(r.id);
                    asserter.putData("reservation.id", r.id);
                });

        asserter.assertEquals(() -> Reservation.count(), 1L);
        asserter.assertThat(() -> Reservation.<Reservation>findById(asserter.getData("reservation.id")),
                persistedReservation -> {
                    Assertions.assertNotNull(persistedReservation);
                    Assertions.assertEquals(reservation.carId, persistedReservation.carId);
                });
    }
}