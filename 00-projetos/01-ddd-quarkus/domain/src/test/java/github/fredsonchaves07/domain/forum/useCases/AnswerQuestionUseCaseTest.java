package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.domain.forum.usecases.answerquestion.AnswerQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.answerquestion.AnswerQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.answerquestion.AnswerQuestionUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerQuestionUseCaseTest {

    private static AnswerQuestionUseCase useCase;

    @BeforeAll
    static void setup() {
        FakeAnswersRepository repository = FakeAnswersRepository.createRepository();
        useCase = new AnswerQuestionUseCase(repository);
    }

    @Test
    public void shouldCreateAnAnswer() {
        AnswerQuestionInput answerQuestionInput = new AnswerQuestionInput(
                "1", "1", "Nova resposta");
        Either<Error, AnswerQuestionOutput> answerQuestionOutput = useCase.execute(answerQuestionInput);
        assertEquals(answerQuestionInput.content(), answerQuestionOutput.getSuccess().get().content());
        assertTrue(answerQuestionOutput.isSuccess());
        assertFalse(answerQuestionOutput.isError());
    }
}
