package github.fredsonchaves07.domain.notification.usecases.readnotification;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record ReadNotificationInput(
        String notifationId
) implements ValueObject {
}
