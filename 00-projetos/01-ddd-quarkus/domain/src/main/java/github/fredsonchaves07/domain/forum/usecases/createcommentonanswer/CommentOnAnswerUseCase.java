package github.fredsonchaves07.domain.forum.usecases.createcommentonanswer;

import github.fredsonchaves07.core.usecases.UseCase;
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
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.commentonquestion.CommentOnQuestionOutput;

import java.util.Objects;

public class CommentOnAnswerUseCase implements UseCase<CommentOnAnswerInput, CommentOnAnswerOutput> {

    private final AnswersRepository answersRepository;

    private final CommentRepository commentRepository;

    public CommentOnAnswerUseCase(AnswersRepository answersRepository, CommentRepository commentRepository) {
        this.answersRepository = Objects.requireNonNull(answersRepository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public CommentOnAnswerOutput execute(CommentOnAnswerInput input) {
        Answer answer = answersRepository
                .findById(new AnswerID(input.answerId()))
                .orElseThrow(() -> new Error("Question not found"));
        Comment comment = Comment.create(new AuthorID(input.authorId()), input.content());
        commentRepository.create(comment);
        answer.addComment(new CommentID(comment.id()));
        return new CommentOnAnswerOutput(comment.authorID().toString(), comment.content());
    }
}
