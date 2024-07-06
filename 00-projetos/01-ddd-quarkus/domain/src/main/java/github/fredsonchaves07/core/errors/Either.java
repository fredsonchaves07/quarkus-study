package github.fredsonchaves07.core.errors;

public final class Either<S, F> {

    private S successValue;

    private F failureValue;

    private Either(S successValue, F failureValue) {
        this.successValue = successValue;
        this.failureValue = failureValue;
    }

    public static<S, F> Either<S, F> success(S value) {
        return new Either<>(value, null);
    }

    public static<S, F> Either<S, F> failure(F value) {
        return new Either<>(null, value);
    }
}
