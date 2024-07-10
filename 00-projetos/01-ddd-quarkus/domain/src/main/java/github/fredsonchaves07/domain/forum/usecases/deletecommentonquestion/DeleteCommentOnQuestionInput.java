package github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record DeleteCommentOnQuestionInput(
        String authorId,
        String commentId,
        String questionId
) implements ValueObject {

}
