package github.fredsonchaves07.domain.forum.usecases.createquestion;

public record CreateQuestionOutput(
        String questionId,
        String authorId,
        String title,
        String content
) {
}
