package github.fredsonchaves07.domain.forum.usecases.editanswer;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.SingleUseCase;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.errors.AnswerNotFoundError;
import github.fredsonchaves07.domain.forum.errors.NotAllowedError;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;

import java.util.Objects;
import java.util.Optional;

public class EditAnswerUseCase implements SingleUseCase<EditAnswerInput> {

    private final AnswersRepository repository;

    public EditAnswerUseCase(AnswersRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<Error, ValueObject> execute(EditAnswerInput input) {
        Optional<Answer> answer = repository.findById(new AnswerID(input.answerId()));
        if (answer.isEmpty())
            return Either.error(AnswerNotFoundError.trows());
        if (!answer.get().authorId().toString().equals(input.authorId()))
            return Either.error(NotAllowedError.trows());
        answer.get().content(input.content());
        repository.update(answer.get());
        return Either.success();
    }
}
