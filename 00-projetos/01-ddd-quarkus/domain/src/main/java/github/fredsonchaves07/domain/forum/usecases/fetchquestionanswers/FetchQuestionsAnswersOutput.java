package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record FetchQuestionsAnswersOutput(
        List<AnswersOutput> questions
) implements ValueObject {
}
