package reservation.reservation;

import java.time.LocalDate;

public class Reservation {

    public Long id;

    public String userId;

    public Long carId;

    public LocalDate startDay;

    public LocalDate endDay;

    public boolean isReserved(LocalDate startDay, LocalDate endDay) {
        return (!(this.endDay.isBefore(startDay) ||
                this.startDay.isAfter(endDay)));
    }
}
