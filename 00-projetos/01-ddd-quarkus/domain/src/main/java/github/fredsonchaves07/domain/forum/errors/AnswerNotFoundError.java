package github.fredsonchaves07.domain.forum.errors;

import github.fredsonchaves07.core.errors.Error;

public class AnswerNotFoundError extends Error {

    private final static String ERROR_MESSAGE = "Answer not found.";

    private AnswerNotFoundError() {
        super(ERROR_MESSAGE);
    }

    public static AnswerNotFoundError trows() {
        return new AnswerNotFoundError();
    }
}
