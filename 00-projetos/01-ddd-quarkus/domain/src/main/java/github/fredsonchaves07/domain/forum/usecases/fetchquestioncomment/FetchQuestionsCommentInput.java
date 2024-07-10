package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record FetchQuestionsCommentInput(
        String questionId
) implements ValueObject {
}
