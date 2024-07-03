package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.FetchQuestionsAnswersInput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.FetchQuestionsAnswersOutput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.FetchQuestionsAnswersUseCase;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsInput;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsOutput;
import github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions.FetchRecentQuestionsUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FetchQuestionsAnswersUseCaseTest {

    private static FetchQuestionsAnswersUseCase useCase;

    private static Question question = makeQuestion();

    @BeforeAll
    static void setup() {
        FakeAnswersRepository answersRepository = FakeAnswersRepository.createRepository();
        useCase = new FetchQuestionsAnswersUseCase(answersRepository);
        question = makeQuestion();
        answersRepository.create(makeAnswer(question));
        answersRepository.create(makeAnswer(question));
        answersRepository.create(makeAnswer(question));
    }

    @Test
    public void shouldBeAbleFetchQuestionsAnswers() {
        FetchQuestionsAnswersInput input = new FetchQuestionsAnswersInput(question.id(), 1);
        FetchQuestionsAnswersOutput output = useCase.execute(input);
        assertEquals(3, output.questions().size());
    }
}
