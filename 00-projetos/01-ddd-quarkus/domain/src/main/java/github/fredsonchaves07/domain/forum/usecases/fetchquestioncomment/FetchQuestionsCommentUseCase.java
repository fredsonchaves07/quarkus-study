package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.AnswersOutput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.FetchQuestionsAnswersInput;
import github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers.FetchQuestionsAnswersOutput;

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
    public FetchQuestionsCommentOutput execute(FetchQuestionsCommentInput input) {
        Question question = repository
                .findById(new QuestionID(input.questionId()))
                .orElseThrow(() -> new Error("Question not found"));
        List<CommentID> commentIDS =  question.comments();
        List<Comment> comments = new ArrayList<>();
        for (CommentID id : commentIDS) {
           Comment comment = commentRepository.findById(id).orElseThrow();
            comments.add(comment);
        }
        return new FetchQuestionsCommentOutput(comments
                .stream()
                .map(CommentsOutput::from)
                .toList());
    }
}
