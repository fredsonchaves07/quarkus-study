package github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record ChooseQuestionBestAnswerInput(
        String anwerId, String authorId
) implements ValueObject {
}
