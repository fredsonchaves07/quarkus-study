package github.fredsonchaves07.domain.notification.usecases.readnotification;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.notification.entities.notification.Notification;
import github.fredsonchaves07.domain.notification.entities.notification.NotificationID;
import github.fredsonchaves07.domain.notification.repositories.NotificationRepository;
import github.fredsonchaves07.domain.notification.usecases.sendnotification.SendNotificationInput;
import github.fredsonchaves07.domain.notification.usecases.sendnotification.SendNotificationOutput;

import java.util.Objects;

public class ReadNotificationUseCase implements UseCase<ReadNotificationInput, ReadNotificationOutput> {

    private final NotificationRepository repository;

    public ReadNotificationUseCase(NotificationRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, ReadNotificationOutput> execute(ReadNotificationInput input) {
        Notification notification = repository.findById(new NotificationID(input.notifationId())).orElseThrow();
        notification.read();
        repository.update(notification);
        return Either.success(new ReadNotificationOutput(
                notification.recipientId(),
                notification.title(),
                notification.content(),
                notification.readAt().toString())
        );
    }
}
