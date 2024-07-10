package github.fredsonchaves07.domain.forum.usecases.fetchanswercomment;

import github.fredsonchaves07.core.valueObject.ValueObject;
import github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment.CommentsOutput;

import java.util.List;

public record FetchAnswerCommentOutput(
        List<CommentsOutput> questions
) implements ValueObject {
}
