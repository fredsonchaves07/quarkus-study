package github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.SingleUseCase;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.errors.CommentNotFoundError;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;
import java.util.Optional;

public class DeleteCommentOnQuestionUseCase implements SingleUseCase<DeleteCommentOnQuestionInput> {

    private final QuestionRepository questionRepository;

    private final CommentRepository commentRepository;

    public DeleteCommentOnQuestionUseCase(QuestionRepository repository, CommentRepository commentRepository) {
        this.questionRepository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, ValueObject> execute(DeleteCommentOnQuestionInput input) {
        Optional<Question> question = questionRepository.findById(new QuestionID(input.questionId()));
        if (question.isEmpty())
            return Either.error(QuestionNotFoundError.trows());
        Optional<Comment> comment = commentRepository.findById(new CommentID(input.commentId()));
        if (comment.isEmpty())
            return Either.error(CommentNotFoundError.trows());
        if (!comment.get().authorID().equals(new AuthorID(input.authorId())))
            return Either.error(CommentNotFoundError.trows());
        question.get().removeComment(new CommentID(comment.get().id()));
        commentRepository.delete(comment.get());
        return null;
    }
}
