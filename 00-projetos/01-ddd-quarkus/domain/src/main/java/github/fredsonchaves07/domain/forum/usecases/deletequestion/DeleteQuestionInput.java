package github.fredsonchaves07.domain.forum.usecases.deletequestion;

public record DeleteQuestionInput(
        String authorId,
        String questionId
) {
}
