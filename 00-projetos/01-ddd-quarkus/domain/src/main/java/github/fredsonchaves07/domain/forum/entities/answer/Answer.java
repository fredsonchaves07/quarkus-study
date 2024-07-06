package github.fredsonchaves07.domain.forum.entities.answer;

import github.fredsonchaves07.core.entities.Entity;
import github.fredsonchaves07.core.entities.Identifier;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

import java.util.ArrayList;
import java.util.List;

public class Answer extends Entity<AnswerID> {

    private String content;

    private final AuthorID authorId;

    private final QuestionID questionId;

    private List<CommentID> comments = new ArrayList<>();

    private Answer(String content, AuthorID authorId, QuestionID questionId) {
        super(new AnswerID());
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    private Answer(AnswerID id, String content, AuthorID authorId, QuestionID questionId) {
        super(id);
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    public static Answer createAnswer(String content, AuthorID authorId, QuestionID questionId) {
        return new Answer(content, authorId, questionId);
    }

    public static Answer createAnswer(AnswerID id, String content, AuthorID authorId, QuestionID questionId) {
        return new Answer(id, content, authorId, questionId);
    }

    public String content() {
        return content;
    }

    public void content(String content) {
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
        super.setUpdatedAt();
    }

    public String excerpt() {
        return this.content.substring(0, 120).trim().concat("...");
    }

    public AuthorID authorId() {
        return authorId;
    }

    public QuestionID questionId() {
        return questionId;
    }

    public Answer addComment(CommentID commentID) {
        this.comments.add(commentID);
        return this;
    }

    public List<CommentID> comments() {
        return new ArrayList<>(this.comments);
    }
}
