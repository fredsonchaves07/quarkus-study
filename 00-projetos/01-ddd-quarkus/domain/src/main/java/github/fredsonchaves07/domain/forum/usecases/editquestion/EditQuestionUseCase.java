package github.fredsonchaves07.domain.forum.usecases.editquestion;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.SingleUseCase;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.errors.NotAllowedError;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;
import java.util.Optional;

public class EditQuestionUseCase implements SingleUseCase<EditQuestionInput> {

    private final QuestionRepository repository;

    public EditQuestionUseCase(QuestionRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, ValueObject> execute(EditQuestionInput input) {
        Optional<Question> question = repository.findById(new QuestionID(input.questionId()));
        if (question.isEmpty())
            return Either.error(QuestionNotFoundError.trows());
        if (!question.get().authorId().toString().equals(input.authorId()))
            return Either.error(NotAllowedError.trows());
        question.get().title(input.title());
        question.get().title(input.content());
        repository.update(question.get());
        return Either.success();
    }
}
