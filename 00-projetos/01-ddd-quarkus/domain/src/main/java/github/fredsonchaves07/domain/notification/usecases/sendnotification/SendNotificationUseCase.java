package github.fredsonchaves07.domain.notification.usecases.sendnotification;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.attachment.AttachmentID;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.notification.entities.notification.Notification;
import github.fredsonchaves07.domain.notification.repositories.NotificationRepository;

import java.util.List;
import java.util.Objects;

public class SendNotificationUseCase implements UseCase<SendNotificationInput, SendNotificationOutput> {

    private final NotificationRepository repository;

    public SendNotificationUseCase(NotificationRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, SendNotificationOutput> execute(SendNotificationInput input) {
        String content = input.content();
        String recipientId = input.recipientId();
        String title = input.title();
        Notification notification = Notification.create(title, content, recipientId);
        repository.create(notification);
        return Either.success(new SendNotificationOutput(
                notification.recipientId(),
                notification.title(),
                notification.content(),
                notification.readAt().toString())
        );
    }
}
