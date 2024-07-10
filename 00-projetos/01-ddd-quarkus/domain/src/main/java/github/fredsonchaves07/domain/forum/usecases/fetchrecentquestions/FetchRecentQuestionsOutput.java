package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;

import github.fredsonchaves07.core.valueObject.ValueObject;

import java.util.List;

public record FetchRecentQuestionsOutput(
        List<RecentQuestionOutput> questions
) implements ValueObject {
}
