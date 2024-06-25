package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionUseCase;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugInput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugOutput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetQuestionBySlugTest {

    private static CreateQuestionUseCase createQuestionUseCase;

    private static GetQuestionBySlugUseCase getQuestionBySlugUseCase;

    private static CreateQuestionOutput createQuestionOutput;

    @BeforeAll
    static void setup() {
        FakeQuestionsRepository repository = FakeQuestionsRepository.createRepository();
        createQuestionUseCase = new CreateQuestionUseCase(repository);
        getQuestionBySlugUseCase = new GetQuestionBySlugUseCase(repository);
        createQuestionOutput = createQuestionUseCase.execute(new CreateQuestionInput(
                "1", "Nova pergunta", "Conteúdo da perguta"
        ));
    }

    @Test
    public void getQuestionBySlug() {
        GetQuestionBySlugInput input = new GetQuestionBySlugInput("nova-pergunta");
        GetQuestionBySlugOutput output = getQuestionBySlugUseCase.execute(input);
        assertNotNull(createQuestionOutput.questionId(), output.questionId());
        assertEquals(createQuestionOutput.title(), output.title());
        assertEquals(createQuestionOutput.authorId(), output.authorId());
        assertEquals(createQuestionOutput.content(), output.content());
    }
}
