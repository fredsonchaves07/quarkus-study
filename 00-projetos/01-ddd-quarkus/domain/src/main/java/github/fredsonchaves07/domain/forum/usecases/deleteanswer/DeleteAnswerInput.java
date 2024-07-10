package github.fredsonchaves07.domain.forum.usecases.deleteanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record DeleteAnswerInput (
        String authorId,
        String answerId
) implements ValueObject {
}
