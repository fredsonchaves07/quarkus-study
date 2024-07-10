package github.fredsonchaves07.domain.forum.usecases.createquestion;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record CreateQuestionOutput(
        String questionId,
        String authorId,
        String title,
        String content
) implements ValueObject {
}
