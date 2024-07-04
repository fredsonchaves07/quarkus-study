package github.fredsonchaves07.domain.forum.usecases.commentonquestion;

public record CommentOnQuestionInput(
        String authorId,
        String questionId,
        String content
) {

}
