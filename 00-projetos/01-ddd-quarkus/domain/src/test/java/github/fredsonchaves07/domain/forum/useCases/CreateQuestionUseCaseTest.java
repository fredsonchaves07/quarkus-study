package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateQuestionUseCaseTest {

    private static CreateQuestionUseCase useCase;

    @BeforeAll
    static void setup() {
        FakeQuestionsRepository repository = FakeQuestionsRepository.createRepository();
        useCase = new CreateQuestionUseCase(repository);
    }

    @Test
    public void createAQuestion() {
        CreateQuestionInput input = new CreateQuestionInput(
                "1", "Nova pergunta", "Conteúdo da perguta"
        );
        CreateQuestionOutput output = useCase.execute(input);
        assertNotNull(output.questionId());
        assertEquals(input.title(), output.title());
        assertEquals(input.authorId(), output.authorId());
        assertEquals(input.content(), output.content());
    }
}
