package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record GetQuestionBySlugInput(
        String slug
) implements ValueObject {
}
