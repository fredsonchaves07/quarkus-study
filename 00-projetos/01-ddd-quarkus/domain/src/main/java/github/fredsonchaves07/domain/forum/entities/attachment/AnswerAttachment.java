package github.fredsonchaves07.domain.forum.entities.attachment;

import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;

public class AnswerAttachment implements ValueObject {

    private final AnswerID answer;

    private final AttachmentID attachmet;

    private AnswerAttachment(AnswerID answer, AttachmentID attachment) {
        this.answer = answer;
        this.attachmet = attachment;
    }

    public static AnswerAttachment create(AnswerID answer, AttachmentID attachment) {
        return new AnswerAttachment(answer, attachment);
    }

    public AnswerID answer() {
        return answer;
    }

    public AttachmentID attachmet() {
        return attachmet;
    }
}
