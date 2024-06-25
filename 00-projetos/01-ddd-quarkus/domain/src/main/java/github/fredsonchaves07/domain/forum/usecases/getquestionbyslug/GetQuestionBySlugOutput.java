package github.fredsonchaves07.domain.forum.usecases.getquestionbyslug;

public record GetQuestionBySlugOutput(
        String questionId,
        String authorId,
        String title,
        String content
) {
}
