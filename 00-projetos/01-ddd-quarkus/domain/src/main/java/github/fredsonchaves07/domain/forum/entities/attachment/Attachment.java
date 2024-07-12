package github.fredsonchaves07.domain.forum.entities.attachment;

import github.fredsonchaves07.core.entities.Entity;

public class Attachment extends Entity<AttachmentID> {

    private String title;

    private String link;

    private Attachment(String title, String link) {
        super(new AttachmentID(title));
        this.title = title;
        this.link = link;
    }

    private Attachment(AttachmentID attachmentID, String title, String link) {
        super(attachmentID);
        this.title = title;
        this.link = link;
    }

    public static Attachment create(String title, String link) {
        return new Attachment(title, link);
    }

    public static Attachment create(AttachmentID attachmentID, String title, String link) {
        return new Attachment(attachmentID, title, link);
    }

    public String title() {
        return title;
    }

    public String link() {
        return link;
    }
}
