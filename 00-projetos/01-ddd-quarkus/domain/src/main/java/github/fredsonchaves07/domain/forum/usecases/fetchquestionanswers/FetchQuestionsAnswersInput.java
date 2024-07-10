package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record FetchQuestionsAnswersInput(
        String questionId,
        int page
) implements ValueObject {
}
