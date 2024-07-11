package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.db.repositories.forum.FakeCommentRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer.ChooseQuestionBestAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeComment.*;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class CommentOnQuestionUseCaseTest {

    private static CommentOnQuestionUseCase useCase;

    private final static Question question = makeQuestion();

    static CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        QuestionRepository questionRepository = FakeQuestionsRepository.createRepository();
        commentRepository = FakeCommentRepository.createRepository();
        useCase = new CommentOnQuestionUseCase(questionRepository, commentRepository);
        questionRepository.create(question);
    }

    @Test
    public void shouldBeCommentOnQuestion() {
        CommentOnQuestionInput input = new CommentOnQuestionInput(
                makeCommentAuthorId().toString(), question.id(), makeCommentContent()
        );
        Either<Error, CommentOnQuestionOutput> output = useCase.execute(input);
        assertEquals(input.authorId(), output.getSuccess().get().authorId());
        assertEquals(input.content(), output.getSuccess().get().content());
        assertFalse(question.comments().isEmpty());
        assertTrue(output.isSuccess());
        assertFalse(output.isError());
    }

    @Test
    public void shouldNotBeCommentOnQuestionFromAnotherUser() {
        CommentOnQuestionInput input = new CommentOnQuestionInput(
                makeCommentAuthorId().toString(), "123", makeCommentContent()
        );
        Either<Error, CommentOnQuestionOutput> output = useCase.execute(input);
        assertTrue(output.isError());
        assertFalse(output.isSuccess());
        assertTrue(output.getError().isPresent());
    }
}
