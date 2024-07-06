package github.fredsonchaves07.domain.forum.usecases.deletecommentonquestion;

public record DeleteCommentOnQuestionInput(
        String authorId,
        String commentId,
        String questionId
) {

}
