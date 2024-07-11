package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.db.repositories.forum.FakeAnswersRepository;
import github.fredsonchaves07.db.repositories.forum.FakeCommentRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer.DeleteCommentOnAnswerInput;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer.DeleteCommentOnAnswerUseCase;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion.DeleteCommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion.DeleteCommentOnQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeAnswer.makeAnswer;
import static github.fredsonchaves07.factories.MakeComment.makeComment;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommentOnAnswerUseCaseTest {

    private static DeleteCommentOnAnswerUseCase useCase;

    private final static Answer answer = makeAnswer();

    private final static Comment comment = makeComment();

    @BeforeEach
    void setUp() {
        AnswersRepository answersRepository = FakeAnswersRepository.createRepository();
        CommentRepository commentRepository = FakeCommentRepository.createRepository();
        useCase = new DeleteCommentOnAnswerUseCase(answersRepository, commentRepository);
        answer.addComment(new CommentID(comment.id()));
        commentRepository.create(comment);
        answersRepository.create(answer);
    }

    @Test
    public void shouldBeCommentOnQuestion() {
        DeleteCommentOnAnswerInput input = new DeleteCommentOnAnswerInput(
                comment.authorID().toString(), comment.id(), answer.id()
        );
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isSuccess());
    }

    @Test
    public void shouldNotBeCommentOnQuestionFromAnotherUser() {
        DeleteCommentOnAnswerInput input = new DeleteCommentOnAnswerInput(
                comment.authorID().toString(), comment.id(), "123"
        );
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isError());
    }
}
