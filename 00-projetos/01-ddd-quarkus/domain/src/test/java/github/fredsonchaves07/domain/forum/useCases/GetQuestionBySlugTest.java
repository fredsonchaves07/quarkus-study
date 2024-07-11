package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugInput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugOutput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetQuestionBySlugTest {

    private static GetQuestionBySlugUseCase getQuestionBySlugUseCase;

    private final static Question newQuestion = makeQuestion();

    @BeforeAll
    static void setup() {
        FakeQuestionsRepository repository = FakeQuestionsRepository.createRepository();
        getQuestionBySlugUseCase = new GetQuestionBySlugUseCase(repository);
        repository.create(newQuestion);
    }

    @Test
    public void getQuestionBySlug() {
        GetQuestionBySlugInput input = new GetQuestionBySlugInput(newQuestion.slug().value());
        Either<Error, GetQuestionBySlugOutput> output = getQuestionBySlugUseCase.execute(input);
        assertNotNull(newQuestion.id(), output.getSuccess().get().questionId());
        assertEquals(newQuestion.title(), output.getSuccess().get().title());
        assertEquals(newQuestion.authorId().toString(), output.getSuccess().get().authorId());
        assertEquals(newQuestion.content(), output.getSuccess().get().content());
        assertTrue(output.isSuccess());
        assertFalse(output.isError());
    }
}
