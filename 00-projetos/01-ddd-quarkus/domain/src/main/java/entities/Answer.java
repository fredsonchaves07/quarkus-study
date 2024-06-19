package entities;

import java.util.UUID;

public class Answer {

    private String id;

    private String content;

    private String authorId;

    private String questionId;

    private Answer(String content, String authorId, String questionId) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    private Answer(String id, String content, String authorId, String questionId) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    public static Answer createAnswer(String content, String authorId, String questionId) {
        return new Answer(content, authorId, questionId);
    }

    public static Answer createAnswer(String id, String content, String authorId, String questionId) {
        return new Answer(id, content, authorId, questionId);
    }

    public String content() {
        return content;
    }
}
