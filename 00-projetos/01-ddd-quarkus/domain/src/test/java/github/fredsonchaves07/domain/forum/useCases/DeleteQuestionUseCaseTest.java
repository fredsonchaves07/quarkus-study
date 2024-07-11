package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteQuestionUseCaseTest {

    private static DeleteQuestionUseCase useCase;

    private final static Question question = makeQuestion();

    @BeforeEach
    void setUp() {
        QuestionRepository repository = FakeQuestionsRepository.createRepository();
        useCase = new DeleteQuestionUseCase(repository);
        repository.create(question);
    }

    @Test
    public void shouldBeDeleteQuestion() {
        DeleteQuestionInput input = new DeleteQuestionInput(question.authorId().toString(), question.id());
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isSuccess());
    }

    @Test
    public void shouldNotBeAbleToDeleteAQuestionFromAnotherUser() {
        DeleteQuestionInput input = new DeleteQuestionInput("123", question.id());
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isError());
    }
}
