package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsInput;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsOutput;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsUseCase;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugInput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugOutput;
import github.fredsonchaves07.domain.forum.usecases.getquestionbyslug.GetQuestionBySlugUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FetchRecentQuestionsUseCaseTest {

    private static FetchRecentQuestionsUseCase useCase;

    private final static Question question1 = makeQuestion();

    private final static Question question2 = makeQuestion();

    private final static Question question3 = makeQuestion();

    @BeforeAll
    static void setup() {
        FakeQuestionsRepository repository = FakeQuestionsRepository.createRepository();
        useCase = new FetchRecentQuestionsUseCase(repository);
        repository.create(question1);
        repository.create(question2);
        repository.create(question3);
    }

    @Test
    public void shouldBeAbleFetchRecentQuestions() {
        FetchRecentQuestionsInput input = new FetchRecentQuestionsInput(1);
        FetchRecentQuestionsOutput output = useCase.execute(input);
        assertEquals(3, output.questions().size());
        assertEquals(question3.id(), output.questions().get(0).questionId());
        assertEquals(question3.authorId().toString(), output.questions().get(0).authorId());
        assertEquals(question3.title(), output.questions().get(0).title());
        assertEquals(question3.content(), output.questions().get(0).content());
        assertEquals(question2.id(), output.questions().get(1).questionId());
        assertEquals(question2.authorId().toString(), output.questions().get(1).authorId());
        assertEquals(question2.title(), output.questions().get(1).title());
        assertEquals(question2.content(), output.questions().get(1).content());
        assertEquals(question1.id(), output.questions().get(2).questionId());
        assertEquals(question1.authorId().toString(), output.questions().get(2).authorId());
        assertEquals(question1.title(), output.questions().get(2).title());
        assertEquals(question1.content(), output.questions().get(2).content());
    }
}
