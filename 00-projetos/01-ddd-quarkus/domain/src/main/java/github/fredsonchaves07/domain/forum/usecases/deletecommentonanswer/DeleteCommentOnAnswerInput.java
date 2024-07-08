package github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record DeleteCommentOnAnswerInput(
        String authorId,
        String commentId,
        String answerId
) implements ValueObject {

}
