package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer.ChooseQuestionBestAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer.ChooseQuestionBestAnswerOutput;
import github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer.ChooseQuestionBestAnswerUseCase;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.deletequestion.DeleteQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class ChooseQuestionBestAnswerUseCaseTest {

    private static ChooseQuestionBestAnswerUseCase useCase;

    private final static Question question = makeQuestion();

    private final static Answer answer = makeAnswer(question);

    @BeforeEach
    void setUp() {
        QuestionRepository questionRepository = FakeQuestionsRepository.createRepository();
        AnswersRepository answersRepository = FakeAnswersRepository.createRepository();
        questionRepository.create(question);
        answersRepository.create(answer);
        useCase = new ChooseQuestionBestAnswerUseCase(questionRepository, answersRepository);
    }

    @Test
    public void shouldBeChooseQuestionBestAnswer() {
        ChooseQuestionBestAnswerInput input = new ChooseQuestionBestAnswerInput(
                answer.id(), answer.authorId().toString()
        );
        Either<Error, ChooseQuestionBestAnswerOutput> output = useCase.execute(input);
        assertEquals(question.id(), output.getSuccess().get().questionId());
        assertEquals(question.authorId().toString(), output.getSuccess().get().authorId());
        assertEquals(question.title(), output.getSuccess().get().title());
        assertEquals(question.content(), output.getSuccess().get().content());
        assertTrue(output.isSuccess());
        assertFalse(output.isError());
    }

    @Test
    public void shouldNotBeChooseQuestionBestAnswerFromAnotherUser() {
        ChooseQuestionBestAnswerInput input = new ChooseQuestionBestAnswerInput(
                answer.id(), "123"
        );
        Either<Error, ChooseQuestionBestAnswerOutput> output = useCase.execute(input);
        assertTrue(output.isError());
        assertFalse(output.isSuccess());
        assertTrue(output.getError().isPresent());
    }
}
