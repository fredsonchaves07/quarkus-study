package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FetchQuestionsCommentUseCase implements UseCase<FetchQuestionsCommentInput, FetchQuestionsCommentOutput> {

    private final QuestionRepository repository;

    private final CommentRepository commentRepository;

    public FetchQuestionsCommentUseCase(QuestionRepository repository, CommentRepository commentRepository) {
        this.repository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, FetchQuestionsCommentOutput> execute(FetchQuestionsCommentInput input) {
        Optional<Question> question = repository.findById(new QuestionID(input.questionId()));
        if (question.isEmpty())
            return Either.error(QuestionNotFoundError.trows());
        List<CommentID> commentIDS =  question.get().comments();
        List<Comment> comments = new ArrayList<>();
        for (CommentID id : commentIDS) {
           Comment comment = commentRepository.findById(id).orElseThrow();
            comments.add(comment);
        }
        return Either.success(new FetchQuestionsCommentOutput(comments
                .stream()
                .map(CommentsOutput::from)
                .toList()));
    }
}
