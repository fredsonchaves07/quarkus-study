package entities;

import entities.valueobjects.Slug;

import java.util.UUID;

public class Question {

    private String id;

    private String title;

    private String content;

    private String authorId;

    private Slug slug;

    private Question(String title, Slug slug, String content, String authorId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
    }

    private Question(String id, String title,  Slug slug, String content, String authorId) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.authorId = authorId;
    }

    public static Question createQuestion(String title, Slug slug, String content, String authorId) {
        return new Question(title, slug, content, authorId);
    }

    public static Question createQuestion(String id, Slug slug, String title, String content, String authorId) {
        return new Question(id, title, slug, content, authorId);
    }
}
