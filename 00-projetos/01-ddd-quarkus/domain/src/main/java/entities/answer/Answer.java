package entities.answer;

import core.entities.Entity;
import core.entities.Identifier;
import entities.question.QuestionID;

public class Answer extends Entity<AnswerID> {

    private final String content;

    private Identifier authorId;

    private QuestionID questionId;

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
}
