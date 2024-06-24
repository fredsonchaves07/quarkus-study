package github.fredsonchaves07.domain.forum.usecases.createquestion;

public record CreateQuestionInput(
        String authorId,
        String title,
        String content
) {
}
