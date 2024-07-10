package github.fredsonchaves07.domain.forum.usecases.editquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record EditQuestionInput(
        String authorId,
        String questionId,
        String title,
        String content
) implements ValueObject {
}
