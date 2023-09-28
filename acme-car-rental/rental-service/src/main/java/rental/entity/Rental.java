package rental.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@MongoEntity(collection = "Rentals")
public class Rental extends PanacheMongoEntity {

    public Long id;

    public String userId;

    public Long reservationId;

    public LocalDate startDate;

    public LocalDate endDate;

    public boolean active;

    public Rental() {}

    public static Optional<Rental> findByUserAndReservationIdsOptional(
            String userId, Long reservationId) {
        return find("userId = ?1 and reservationId = ?2",
                userId, reservationId)
                .firstResultOptional();
    }

    public static List<Rental> listActive() {
        return list("active", true);
    }
}
