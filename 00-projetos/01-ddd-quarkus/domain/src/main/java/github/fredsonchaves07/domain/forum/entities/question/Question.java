package github.fredsonchaves07.domain.forum.entities.question;

import github.fredsonchaves07.core.entities.AggregateRoot;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Question extends AggregateRoot<QuestionID> {

    private String title;

    private String content;

    private AnswerID bestAnswerId;

    private AuthorID authorId;

    private Slug slug;

    private final List<CommentID> comments = new ArrayList<>();

    private final QuestionAttachmentList attachments = new QuestionAttachmentList(new ArrayList<>());

    private Question(String title, Slug slug, String content, AuthorID authorId, AnswerID bestAnswerId) {
        super(new QuestionID());
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
        this.bestAnswerId = bestAnswerId;
    }

    private Question(QuestionID id, String title, Slug slug, String content, AuthorID authorId,  AnswerID bestAnswerId) {
        super(id);
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
        this.bestAnswerId = bestAnswerId;
    }

    private Question(AuthorID authorID, String title, String content) {
        super(new QuestionID());
        this.title = title;
        this.slug = Slug.createFromText(title);
        this.content = content;
        this.authorId = authorID;
    }

    private Question(QuestionID id, AuthorID authorID, String title, String content) {
        super(id);
        this.title = title;
        this.slug = Slug.createFromText(title);
        this.content = content;
        this.authorId = authorID;
    }

    public static Question createQuestion(String title, Slug slug, String content, AuthorID authorId, AnswerID bestAnswerId) {
        return new Question(title, slug, content, authorId, bestAnswerId);
    }

    public static Question createQuestion(QuestionID id, Slug slug, String title, String content, AuthorID authorId, AnswerID bestAnswerId) {
        return new Question(id, title, slug, content, authorId, bestAnswerId);
    }

    public static Question createQuestion(AuthorID authorID, String title, String content) {
        return new Question(authorID, title, content);
    }

    public static Question createQuestion(QuestionID id, AuthorID authorID, String title, String content) {
        return new Question(id, authorID, title, content);
    }

    public boolean isNew() {
        return super.createdAt().until(LocalDateTime.now(), ChronoUnit.DAYS) <= 3;
    }

    public void updateTitle(String title) {
        this.title = title;
        this.slug = Slug.createFromText(title);
        super.setUpdatedAt();
    }

    public void updateContent(String content) {
        this.content = content;
        super.setUpdatedAt();
    }

    public void updateBestAnwserId(AnswerID answerID) {
        this.bestAnswerId = answerID;
        super.setUpdatedAt();
    }

    public String excerpt() {
        return this.content.substring(0, 120).trim().concat("...");
    }

    public AuthorID authorId() {
        return authorId;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public Slug slug() {
        return slug;
    }

    public void title(String title) {
        if (title != null) this.title = title;
    }

    public void content(String content) {
        if (content != null) this.content = content;
    }

    public Question addComment(CommentID commentID) {
        this.comments.add(commentID);
        return this;
    }

    public Question removeComment(CommentID commentID) {
        this.comments.remove(commentID);
        return this;
    }

    public List<CommentID> comments() {
        return new ArrayList<>(this.comments);
    }

    public QuestionAttachmentList attachments() {
        return attachments;
    }

    public Question addAttachment(QuestionAttachment attachment) {
        this.attachments.add(attachment);
        return this;
    }
}
