package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record GetQuestionBySlugOutput(
        String questionId,
        String authorId,
        String title,
        String content
) implements ValueObject {
}
