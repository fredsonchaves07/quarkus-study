package github.fredsonchaves07.domain.forum.usecases.fetchquestioncomment;


import github.fredsonchaves07.domain.forum.entities.comment.Comment;

public record CommentsOutput(
        String authorId,
        String content
){

    public static CommentsOutput from(Comment comment) {
        return new CommentsOutput(
                comment.authorID().toString(), comment.content()
        );
    }
}