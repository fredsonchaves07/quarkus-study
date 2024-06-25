package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugInput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugOutput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        GetQuestionBySlugOutput output = getQuestionBySlugUseCase.execute(input);
        assertNotNull(newQuestion.id(), output.questionId());
        assertEquals(newQuestion.title(), output.title());
        assertEquals(newQuestion.authorId().toString(), output.authorId());
        assertEquals(newQuestion.content(), output.content());
    }
}
