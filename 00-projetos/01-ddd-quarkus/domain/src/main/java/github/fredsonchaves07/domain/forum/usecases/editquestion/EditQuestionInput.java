package github.fredsonchaves07.domain.forum.usecases.editquestion;

public record EditQuestionInput(
        String authorId,
        String questionId,
        String title,
        String content
) {
}
