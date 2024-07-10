package github.fredsonchaves07.domain.forum.usecases.createcommentonanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CommentOnAnswerInput(
        String authorId,
        String answerId,
        String content
) implements ValueObject {

}
