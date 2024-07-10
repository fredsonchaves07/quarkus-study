package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record FetchQuestionsCommentOutput(
        List<CommentsOutput> questions
) implements ValueObject {
}
