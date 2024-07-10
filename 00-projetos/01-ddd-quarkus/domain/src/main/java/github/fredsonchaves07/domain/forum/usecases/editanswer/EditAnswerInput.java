package github.fredsonchaves07.domain.forum.usecases.editanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record EditAnswerInput(
        String authorId,
        String answerId,
        String content
) implements ValueObject {
}
