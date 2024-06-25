package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.createquestion.CreateQuestionUseCase;
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

    private static Question newQuestion;

    @BeforeAll
    static void setup() {
        FakeQuestionsRepository repository = FakeQuestionsRepository.createRepository();
        repository.create(makeQuestion());
    }

    @Test
    public void getQuestionBySlug() {
        GetQuestionBySlugInput input = new GetQuestionBySlugInput("nova-pergunta");
        GetQuestionBySlugOutput output = getQuestionBySlugUseCase.execute(input);
        assertNotNull(newQuestion.id(), output.questionId());
        assertEquals(newQuestion.title(), output.title());
        assertEquals(newQuestion.authorId().toString(), output.authorId());
        assertEquals(newQuestion.content(), output.content());
    }
}
