package github.fredsonchaves07.domain.forum.errors;

import github.fredsonchaves07.core.errors.Error;

public class CommentNotFoundError extends Error {

    private final static String ERROR_MESSAGE = "Comment not found.";

    private CommentNotFoundError() {
        super(ERROR_MESSAGE);
    }

    public static CommentNotFoundError trows() {
        return new CommentNotFoundError();
    }
}
