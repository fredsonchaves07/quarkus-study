package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;

import github.fredsonchaves07.core.valueObject.ValueObject;

public record FetchRecentQuestionsInput(
        int page
) implements ValueObject {
}
