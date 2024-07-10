package github.fredsonchaves07.domain.forum.usecases.createcommentonanswer;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.errors.AnswerNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

public class CommentOnAnswerUseCase implements UseCase<CommentOnAnswerInput, CommentOnAnswerOutput> {

    private final AnswersRepository answersRepository;

    private final CommentRepository commentRepository;

    public CommentOnAnswerUseCase(AnswersRepository answersRepository, CommentRepository commentRepository) {
        this.answersRepository = Objects.requireNonNull(answersRepository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, CommentOnAnswerOutput> execute(CommentOnAnswerInput input) {
        Optional<Answer> answer = answersRepository.findById(new AnswerID(input.answerId()));
        if (answer.isEmpty())
            return Either.error(AnswerNotFoundError.trows());
        Comment comment = Comment.create(new AuthorID(input.authorId()), input.content());
        commentRepository.create(comment);
        answer.get().addComment(new CommentID(comment.id()));
        return Either.success(new CommentOnAnswerOutput(comment.authorID().toString(), comment.content()));
    }
}
