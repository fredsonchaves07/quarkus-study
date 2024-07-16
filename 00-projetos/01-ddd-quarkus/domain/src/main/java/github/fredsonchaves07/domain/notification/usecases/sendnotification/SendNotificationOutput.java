package github.fredsonchaves07.domain.notification.usecases.sendnotification;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record SendNotificationOutput(
        String recipientId,
        String title,
        String content,
        String readAt
) implements ValueObject {
}
