package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.domain.forum.repositories.memorydb.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateQuestionUseCaseTest {

    static FakeQuestionsRepository repository;

    @BeforeAll
    static void setup() {
        repository = FakeQuestionsRepository.createRepository();
    }

    @Test
    public void createAQuestion() {
        CreateQuestionUseCase useCase = new CreateQuestionUseCase(repository);
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
