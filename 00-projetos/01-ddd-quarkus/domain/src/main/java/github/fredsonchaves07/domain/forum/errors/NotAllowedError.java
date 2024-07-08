package github.fredsonchaves07.domain.forum.errors;

import github.fredsonchaves07.core.errors.Error;

public class NotAllowedError extends Error {

    private final static String ERROR_MESSAGE = "Not allowed.";

    private NotAllowedError() {
        super(ERROR_MESSAGE);
    }

    public static NotAllowedError trows() {
        return new NotAllowedError();
    }
}
