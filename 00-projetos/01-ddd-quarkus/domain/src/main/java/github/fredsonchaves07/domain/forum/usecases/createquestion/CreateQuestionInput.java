package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CreateQuestionInput(
        String authorId,
        String title,
        String content
) implements ValueObject {
}
