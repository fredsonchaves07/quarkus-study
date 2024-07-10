package github.fredsonchaves07.domain.forum.errors;

import github.fredsonchaves07.core.errors.Error;

public class QuestionNotFoundError extends Error {

    private final static String ERROR_MESSAGE = "Question not found.";

    private QuestionNotFoundError() {
        super(ERROR_MESSAGE);
    }

    public static QuestionNotFoundError trows() {
        return new QuestionNotFoundError();
    }
}
