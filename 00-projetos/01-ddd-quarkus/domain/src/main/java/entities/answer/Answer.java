package entities.answer;

import core.entities.Entity;
import core.entities.Identifier;
import entities.question.QuestionID;

public class Answer extends Entity<AnswerID> {

    private String content;

    private final Identifier authorId;

    private final QuestionID questionId;

    private Answer(String content, Identifier authorId, QuestionID questionId) {
        super(new AnswerID());
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    private Answer(AnswerID id, String content, Identifier authorId, QuestionID questionId) {
        super(id);
        this.content = content;
        this.authorId = authorId;
        this.questionId = questionId;
    }

    public static Answer createAnswer(String content, Identifier authorId, QuestionID questionId) {
        return new Answer(content, authorId, questionId);
    }

    public static Answer createAnswer(AnswerID id, String content, Identifier authorId, QuestionID questionId) {
        return new Answer(id, content, authorId, questionId);
    }

    public String content() {
        return content;
    }

    public void updateContent(String content) {
        this.content = content;
        super.setUpdatedAt();
    }

    public String excerpt() {
        return this.content.substring(0, 120).trim().concat("...");
    }
}
