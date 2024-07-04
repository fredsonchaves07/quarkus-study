package github.fredsonchaves07.domain.forum.usecases.commentonquestion;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class CommentOnQuestionUseCase implements UseCase<CommentOnQuestionInput, CommentOnQuestionOutput> {

    private final QuestionRepository questionRepository;

    private final CommentRepository commentRepository;

    public CommentOnQuestionUseCase(QuestionRepository repository, CommentRepository commentRepository) {
        this.questionRepository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public CommentOnQuestionOutput execute(CommentOnQuestionInput input) {
        Question question = questionRepository
                .findById(new QuestionID(input.questionId()))
                .orElseThrow(() -> new Error("Question not found"));
        Comment comment = Comment.create(new AuthorID(input.authorId()), input.content());
        commentRepository.create(comment);
        question.addComment(new CommentID(comment.id()));
        return new CommentOnQuestionOutput(comment.authorID().toString(), comment.content());
    }
}
