package github.fredsonchaves07.domain.forum.useCases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.db.repositories.forum.FakeCommentRepository;
import github.fredsonchaves07.db.repositories.forum.FakeQuestionsRepository;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionOutput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionUseCase;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion.DeleteCommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion.DeleteCommentOnQuestionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static github.fredsonchaves07.factories.MakeComment.*;
import static github.fredsonchaves07.factories.MakeQuestion.makeQuestion;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommentOnQuestionUseCaseTest {

    private static DeleteCommentOnQuestionUseCase useCase;

    private final static Question question = makeQuestion();

    private final static Comment comment = makeComment();

    @BeforeEach
    void setUp() {
        QuestionRepository questionRepository = FakeQuestionsRepository.createRepository();
        CommentRepository commentRepository = FakeCommentRepository.createRepository();
        useCase = new DeleteCommentOnQuestionUseCase(questionRepository, commentRepository);
        question.addComment(new CommentID(comment.id()));
        commentRepository.create(comment);
        questionRepository.create(question);
    }

    @Test
    public void shouldBeCommentOnQuestion() {
        DeleteCommentOnQuestionInput input = new DeleteCommentOnQuestionInput(
                comment.authorID().toString(), comment.id(), question.id()
        );
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isSuccess());
    }

    @Test
    public void shouldNotBeCommentOnQuestionFromAnotherUser() {
        DeleteCommentOnQuestionInput input = new DeleteCommentOnQuestionInput(
                comment.authorID().toString(), comment.id(), "123"
        );
        Either<Error, ValueObject> output = useCase.execute(input);
        assertTrue(output.isError());
    }
}
