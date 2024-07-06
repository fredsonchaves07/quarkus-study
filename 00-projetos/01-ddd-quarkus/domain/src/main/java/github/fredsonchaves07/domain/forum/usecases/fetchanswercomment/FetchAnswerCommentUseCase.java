package github.fredsonchaves07.domain.forum.usecases.fetchanswercomment;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment.CommentsOutput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment.FetchQuestionsCommentInput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment.FetchQuestionsCommentOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FetchAnswerCommentUseCase implements UseCase<FetchAnswerCommentInput, FetchAnswerCommentOutput> {

    private final AnswersRepository repository;

    private final CommentRepository commentRepository;

    public FetchAnswerCommentUseCase(AnswersRepository repository, CommentRepository commentRepository) {
        this.repository = Objects.requireNonNull(repository);
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Override
    public FetchAnswerCommentOutput execute(FetchAnswerCommentInput input) {
        Answer answer = repository
                .findById(new AnswerID(input.answerId()))
                .orElseThrow(() -> new Error("Answer not found"));
        List<CommentID> commentIDS =  answer.comments();
        List<Comment> comments = new ArrayList<>();
        for (CommentID id : commentIDS) {
           Comment comment = commentRepository.findById(id).orElseThrow();
            comments.add(comment);
        }
        return new FetchAnswerCommentOutput(comments
                .stream()
                .map(CommentsOutput::from)
                .toList());
    }
}
