package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;

import java.util.List;

public record FetchQuestionsAnswersOutput(
        List<AnswersOutput> questions
) {
}
