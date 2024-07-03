package github.fredsonchaves07.domain.forum.usecases.fetchquestionanswers;

public record FetchQuestionsAnswersInput(
        String questionId,
        int page
) {
}
