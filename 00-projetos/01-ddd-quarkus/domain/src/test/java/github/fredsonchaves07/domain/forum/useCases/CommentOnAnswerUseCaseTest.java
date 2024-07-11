package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.db.repositories.forum.FakeCommentRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionUseCase;
import github.fredsonchaves07.domain.forum.usecases.createcommentonanswer.CommentOnAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.createcommentonanswer.CommentOnAnswerOutput;
import github.fredsonchaves07.domain.forum.usecases.createcommentonanswer.CommentOnAnswerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeComment.makeCommentAuthorId;
import static github.fredsonchaves07.factories.MakeComment.makeCommentContent;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class CommentOnAnswerUseCaseTest {

    private static CommentOnAnswerUseCase useCase;

    private final static Answer answer = makeAnswer();

    static CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        AnswersRepository answersRepository = FakeAnswersRepository.createRepository();
        commentRepository = FakeCommentRepository.createRepository();
        useCase = new CommentOnAnswerUseCase(answersRepository, commentRepository);
        answersRepository.create(answer);
    }

    @Test
    public void shouldBeCommentOnQuestion() {
        CommentOnAnswerInput input = new CommentOnAnswerInput(
                makeCommentAuthorId().toString(), answer.id(), makeCommentContent()
        );
        Either<Error, CommentOnAnswerOutput> output = useCase.execute(input);
        assertEquals(input.authorId(), output.getSuccess().get().authorId());
        assertEquals(input.content(), output.getSuccess().get().content());
        assertFalse(answer.comments().isEmpty());
        assertTrue(output.isSuccess());
        assertFalse(output.isError());
    }

    @Test
    public void shouldNotBeCommentOnQuestionFromAnotherUser() {
        CommentOnAnswerInput input = new CommentOnAnswerInput(
                makeCommentAuthorId().toString(), "123", makeCommentContent()
        );
        Either<Error, CommentOnAnswerOutput> output = useCase.execute(input);
        assertTrue(output.isError());
        assertFalse(output.isSuccess());
        assertTrue(output.getError().isPresent());
    }
}
