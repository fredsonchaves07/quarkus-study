package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.usecases.deleteanswer.DeleteAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.deleteanswer.DeleteAnswerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteAnswerUseCaseTest {

    private static DeleteAnswerUseCase useCase;

    private final static Answer answer = makeAnswer();

    @BeforeEach
    void setUp() {
        AnswersRepository repository = FakeAnswersRepository.createRepository();
        useCase = new DeleteAnswerUseCase(repository);
        repository.create(answer);
    }

    @Test
    public void shouldBeDeleteAnswer() {
        DeleteAnswerInput input = new DeleteAnswerInput(answer.authorId().toString(), answer.id());
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isSuccess());
    }

    @Test
    public void shouldNotBeAbleToDeleteAAnswerFromAnotherUser() {
        DeleteAnswerInput input = new DeleteAnswerInput("123", answer.id());
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isError());
    }
}
