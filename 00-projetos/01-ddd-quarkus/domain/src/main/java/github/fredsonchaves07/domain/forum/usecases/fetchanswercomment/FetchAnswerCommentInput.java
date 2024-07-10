package github.fredsonchaves07.domain.forum.usecases.fetchanswercomment;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record FetchAnswerCommentInput(
        String answerId
) implements ValueObject {
}
