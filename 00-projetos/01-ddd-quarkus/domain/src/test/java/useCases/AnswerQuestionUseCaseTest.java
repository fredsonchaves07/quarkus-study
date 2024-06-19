package useCases;

import factories.repositories.FakeAnswersRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerQuestionUseCaseTest {

    static FakeAnswersRepository repository;

    @BeforeAll
    static void setup() {
        repository = FakeAnswersRepository.createRepository();
    }

    @Test
    public void shouldCreateAnAnswer() {
        AnswerQuestionInput answerQuestionInput = new AnswerQuestionInput(
                "1", "1", "Nova resposta");
        AnswerQuestionUseCase useCase = new AnswerQuestionUseCase(repository);
        AnswerQuestionOutput answerQuestionOutput = useCase.execute(answerQuestionInput);
        assertEquals(answerQuestionInput.content(), answerQuestionOutput.content());
    }
}
