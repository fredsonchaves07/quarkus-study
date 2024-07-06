package github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.Objects;

public class DeleteCommentOnQuestionUseCase implements InputUseCase<DeleteCommentOnQuestionInput> {

    private final QuestionRepository questionRepository;

    private final CommentRepository commentRepository;

    public DeleteCommentOnQuestionUseCase(QuestionRepository repository, CommentRepository commentRepository) {
        this.questionRepository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public void execute(DeleteCommentOnQuestionInput input) {
        Question question = questionRepository
                .findById(new QuestionID(input.questionId()))
                .orElseThrow(() -> new Error("Question not found"));
        Comment comment = commentRepository
                .findById(new CommentID(input.commentId())).
                orElseThrow(() -> new Error("Comment Not Found"));
        if (!comment.authorID().equals(new AuthorID(input.authorId())))
            throw new Error("Not allowed");
        question.removeComment(new CommentID(comment.id()));
        commentRepository.delete(comment);
    }
}
