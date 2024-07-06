package github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer;

import github.fredsonchaves07.core.usecases.InputUseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion.DeleteCommentOnQuestionInput;

import java.util.Objects;

public class DeleteCommentOnAnswerUseCase implements InputUseCase<DeleteCommentOnAnswerInput> {

    private final AnswersRepository answersRepository;

    private final CommentRepository commentRepository;

    public DeleteCommentOnAnswerUseCase(AnswersRepository answersRepository, CommentRepository commentRepository) {
        this.answersRepository = Objects.requireNonNull(answersRepository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public void execute(DeleteCommentOnAnswerInput input) {
        Answer answer = answersRepository
                .findById(new AnswerID(input.answerId()))
                .orElseThrow(() -> new Error("Answer not found"));
        Comment comment = commentRepository
                .findById(new CommentID(input.commentId())).
                orElseThrow(() -> new Error("Comment Not Found"));
        if (!comment.authorID().equals(new AuthorID(input.authorId())))
            throw new Error("Not allowed");
        answer.removeComment(new CommentID(comment.id()));
        commentRepository.delete(comment);
    }
}
