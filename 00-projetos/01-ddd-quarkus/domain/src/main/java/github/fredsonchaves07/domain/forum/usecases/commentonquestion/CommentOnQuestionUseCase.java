package github.fredsonchaves07.domain.forum.usecases.commentonquestion;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.errors.QuestionNotFoundError;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;
import java.util.Optional;

public class CommentOnQuestionUseCase implements UseCase<CommentOnQuestionInput, CommentOnQuestionOutput> {

    private final QuestionRepository questionRepository;

    private final CommentRepository commentRepository;

    public CommentOnQuestionUseCase(QuestionRepository repository, CommentRepository commentRepository) {
        this.questionRepository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public Either<Error, CommentOnQuestionOutput> execute(CommentOnQuestionInput input) {
        Optional<Question> question = questionRepository.findById(new QuestionID(input.questionId()));
        if (question.isEmpty())
            return Either.error(QuestionNotFoundError.trows());
        Comment comment = Comment.create(new AuthorID(input.authorId()), input.content());
        commentRepository.create(comment);
        question.get().addComment(new CommentID(comment.id()));
        return Either.success(new CommentOnQuestionOutput(comment.authorID().toString(), comment.content()));
    }
}
