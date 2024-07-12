package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
                "1", "Nova pergunta", "Conteúdo da perguta", List.of("1", "2")
        );
        Either<Error, CreateQuestionOutput> output = useCase.execute(input);
        assertNotNull(output.getSuccess().get().questionId());
        assertEquals(input.title(), output.getSuccess().get().title());
        assertEquals(input.authorId(), output.getSuccess().get().authorId());
        assertEquals(input.content(), output.getSuccess().get().content());
        assertEquals(input.attachmentId(), output.getSuccess().get().attachmentIds());
        assertTrue(output.isSuccess());
        assertFalse(output.isError());
    }
}
