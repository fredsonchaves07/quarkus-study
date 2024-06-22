package entities.question;

import core.entities.Entity;
import core.entities.Identifier;
import entities.answer.AnswerID;
import entities.valueobjects.Slug;

public class Question extends Entity<QuestionID> {

    private String title;

    private String content;

    private AnswerID bestAnswerId;

    private Identifier authorId;

    private Slug slug;

    private Question(String title, Slug slug, String content, Identifier authorId, AnswerID bestAnswerId) {
        super(new QuestionID());
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
        this.bestAnswerId = bestAnswerId;
    }

    private Question(QuestionID id, String title, Slug slug, String content, Identifier authorId,  AnswerID bestAnswerId) {
        super(id);
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
        this.bestAnswerId = bestAnswerId;
    }

    public static Question createQuestion(String title, Slug slug, String content, Identifier authorId, AnswerID bestAnswerId) {
        return new Question(title, slug, content, authorId, bestAnswerId);
    }

    public static Question createQuestion(QuestionID id, Slug slug, String title, String content, Identifier authorId, AnswerID bestAnswerId) {
        return new Question(id, title, slug, content, authorId, bestAnswerId);
    }
}
