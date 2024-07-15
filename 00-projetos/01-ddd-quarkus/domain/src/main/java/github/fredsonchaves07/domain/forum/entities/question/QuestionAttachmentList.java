package github.fredsonchaves07.domain.forum.entities.question;

import github.fredsonchaves07.core.entities.WatchedList;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;

import java.util.List;

public class QuestionAttachmentList extends WatchedList<QuestionAttachment> {


    public QuestionAttachmentList(List<QuestionAttachment> initialItems) {
        super(initialItems);
    }

    @Override
    public boolean compareItems(QuestionAttachment a, QuestionAttachment b) {
        return a.attachmet().equals(b.attachmet());
    }
}
