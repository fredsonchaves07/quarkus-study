package github.fredsonchaves07.domain.forum.usecases.fetchrecentquestions;

import java.util.List;

public record FetchRecentQuestionsOutput(
        List<RecentQuestionOutput> questions
) {
}
