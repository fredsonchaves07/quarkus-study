package github.fredsonchaves07.domain.forum.usecases.editanswer;

public record EditAnswerInput(
        String authorId,
        String answerId,
        String content
) {
}
