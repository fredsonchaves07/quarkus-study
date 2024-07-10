package github.fredsonchaves07.domain.forum.usecases.deletequestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record DeleteQuestionInput(
        String authorId,
        String questionId
) implements ValueObject {
}
