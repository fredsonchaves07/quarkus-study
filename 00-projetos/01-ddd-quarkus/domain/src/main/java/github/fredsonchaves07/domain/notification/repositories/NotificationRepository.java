package github.fredsonchaves07.domain.notification.repositories;

import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.notification.entities.notification.Notification;
import github.fredsonchaves07.domain.notification.entities.notification.NotificationID;

public interface NotificationRepository extends Repository<NotificationID, Notification> {
}
