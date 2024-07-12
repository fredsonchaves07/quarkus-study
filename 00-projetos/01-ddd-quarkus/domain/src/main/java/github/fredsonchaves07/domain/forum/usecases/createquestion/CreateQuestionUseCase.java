package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.attachment.AttachmentID;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.List;
import java.util.Objects;

public class CreateQuestionUseCase implements UseCase<CreateQuestionInput, CreateQuestionOutput> {

    private final QuestionRepository repository;

    public CreateQuestionUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, CreateQuestionOutput> execute(CreateQuestionInput input) {
        String content = input.content();
        String authorId = input.authorId();
        String title = input.title();
        Question question = Question.createQuestion(new AuthorID(authorId), title, content);
        List<String> questionAttachments = input.attachmentId()
                .stream()
                .map(attachmentQuestion -> QuestionAttachment.create(
                        new QuestionID(question.id()),
                        new AttachmentID(attachmentQuestion))
                )
                .peek(question::addAttachment)
                .map(attachment -> attachment.attachmet().value())
                .toList();
        repository.create(question);
        return Either.success(new CreateQuestionOutput(
                question.id(), authorId, title, content, questionAttachments)
        );
    }
}
