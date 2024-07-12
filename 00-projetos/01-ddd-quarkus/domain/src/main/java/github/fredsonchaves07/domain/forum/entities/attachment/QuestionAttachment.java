package github.fredsonchaves07.domain.forum.entities.attachment;

import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

public class QuestionAttachment implements ValueObject {

    private final QuestionID question;

    private final AttachmentID attachmet;

    private QuestionAttachment(QuestionID question, AttachmentID attachment) {
        this.question = question;
        this.attachmet = attachment;
    }

    public static QuestionAttachment create(QuestionID question, AttachmentID attachment) {
        return new QuestionAttachment(question, attachment);
    }

    public QuestionID question() {
        return question;
    }

    public AttachmentID attachmet() {
        return attachmet;
    }
}
