package github.fredsonchaves07.domain.forum.entities.comment;

import github.fredsonchaves07.core.entities.Entity;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;

public class Comment extends Entity<CommentID> {

    private final AuthorID authorID;

    private String content;

    private Comment(AuthorID authorID, String content) {
        super(new CommentID());
        this.content = content;
        this.authorID = authorID;
    }

    private Comment(CommentID commentID, AuthorID authorID, String content) {
        super(commentID);
        this.content = content;
        this.authorID = authorID;
    }

    public static Comment create(AuthorID authorID, String content) {
        return new Comment(authorID, content);
    }

    public static Comment create(CommentID commentID, AuthorID authorID, String content) {
        return new Comment(commentID, authorID, content);
    }

    public String content() {
        return content;
    }

    public Comment content(String content) {
        this.content = content;
        setUpdatedAt();
        return this;
    }

    public AuthorID authorID() {
        return authorID;
    }
}
