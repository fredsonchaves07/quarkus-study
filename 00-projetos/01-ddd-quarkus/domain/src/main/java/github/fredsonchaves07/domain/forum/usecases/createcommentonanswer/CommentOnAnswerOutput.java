package github.fredsonchaves07.domain.forum.usecases.createcommentonanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CommentOnAnswerOutput(
        String authorId,
        String content
) implements ValueObject {

}
