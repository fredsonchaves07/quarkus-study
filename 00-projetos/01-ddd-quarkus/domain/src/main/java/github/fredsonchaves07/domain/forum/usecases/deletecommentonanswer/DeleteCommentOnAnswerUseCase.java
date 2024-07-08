package github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.errors.AnswerNotFoundError;
import github.fredsonchaves07.domain.forum.errors.CommentNotFoundError;
import github.fredsonchaves07.domain.forum.errors.NotAllowedError;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;

import java.util.Objects;
import java.util.Optional;

public class DeleteCommentOnAnswerUseCase implements UseCase<DeleteCommentOnAnswerInput> {

    private final AnswersRepository answersRepository;

    private final CommentRepository commentRepository;

    public DeleteCommentOnAnswerUseCase(AnswersRepository answersRepository, CommentRepository commentRepository) {
        this.answersRepository = Objects.requireNonNull(answersRepository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, ValueObject> execute(DeleteCommentOnAnswerInput input) {
        Optional<Answer> answer = answersRepository
                .findById(new AnswerID(input.answerId()));
        if (answer.isEmpty())
            return Either.error(AnswerNotFoundError.trows());
        Optional<Comment> comment = commentRepository
                .findById(new CommentID(input.commentId()));
        if (comment.isEmpty())
            return Either.error(CommentNotFoundError.trows());
        if (!comment.get().authorID().equals(new AuthorID(input.authorId())))
            return Either.error(NotAllowedError.trows());
        answer.get().removeComment(new CommentID(comment.get().id()));
        commentRepository.delete(comment.get());
        return Either.success(null);
    }
}
