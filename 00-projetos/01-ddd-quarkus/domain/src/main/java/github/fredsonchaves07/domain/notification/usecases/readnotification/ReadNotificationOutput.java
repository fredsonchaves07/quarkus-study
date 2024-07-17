package github.fredsonchaves07.domain.notification.usecases.readnotification;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record ReadNotificationOutput(
        String recipientId,
        String title,
        String content,
        String readAt
) implements ValueObject {
}
