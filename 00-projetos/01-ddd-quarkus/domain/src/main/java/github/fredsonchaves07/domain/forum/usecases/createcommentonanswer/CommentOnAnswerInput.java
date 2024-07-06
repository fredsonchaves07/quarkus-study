package github.fredsonchaves07.domain.forum.usecases.createcommentonanswer;

public record CommentOnAnswerInput(
        String authorId,
        String answerId,
        String content
) {

}
