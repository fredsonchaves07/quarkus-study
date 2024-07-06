package github.fredsonchaves07.domain.forum.usecases.deletecommentonanswer;

public record DeleteCommentOnAnswerInput(
        String authorId,
        String commentId,
        String answerId
) {

}
