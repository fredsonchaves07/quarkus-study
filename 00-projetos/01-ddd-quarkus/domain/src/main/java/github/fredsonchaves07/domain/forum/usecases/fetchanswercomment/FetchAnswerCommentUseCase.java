package github.fredsonchaves07.domain.forum.usecases.fetchanswercomment;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.errors.AnswerNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment.CommentsOutput;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FetchAnswerCommentUseCase implements UseCase<FetchAnswerCommentInput, FetchAnswerCommentOutput> {

    private final AnswersRepository repository;

    private final CommentRepository commentRepository;

    public FetchAnswerCommentUseCase(AnswersRepository repository, CommentRepository commentRepository) {
        this.repository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, FetchAnswerCommentOutput> execute(FetchAnswerCommentInput input) {
        Optional<Answer> answer = repository.findById(new AnswerID(input.answerId()));
        if (answer.isEmpty())
            return Either.error(AnswerNotFoundError.trows());
        List<CommentID> commentIDS =  answer.get().comments();
        List<Comment> comments = new ArrayList<>();
        for (CommentID id : commentIDS) {
           Comment comment = commentRepository.findById(id).orElseThrow();
            comments.add(comment);
        }
        return Either.success(new FetchAnswerCommentOutput(comments
                .stream()
                .map(CommentsOutput::from)
                .toList())) ;
    }
}
