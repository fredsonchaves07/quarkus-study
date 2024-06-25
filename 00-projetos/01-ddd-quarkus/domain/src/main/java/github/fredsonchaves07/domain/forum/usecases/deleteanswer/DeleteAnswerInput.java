package github.fredsonchaves07.domain.forum.usecases.deleteanswer;

public record DeleteAnswerInput(
        String authorId,
        String answerId
) {
}
