package github.fredsonchaves07.core.errors;

import github.fredsonchaves07.core.valueObject.ValueObject;

public abstract class Either<E extends  Error, S extends ValueObject> {

    private Either() {

    }

    public abstract boolean isError();

    public abstract boolean isSuccess();

    public abstract E getError();

    public abstract S getSuccess();

    public static <E extends github.fredsonchaves07.core.errors.Error,
            S extends ValueObject> Either<E, S> error(E value) {
        return new Error<>(value);
    }

    public static <E extends github.fredsonchaves07.core.errors.Error,
            S extends ValueObject> Either<E, S> success(S value) {
        return new Success<>(value);
    }

    private static class Error<E extends github.fredsonchaves07.core.errors.Error,
            S extends ValueObject> extends Either<E, S> {
        private final E value;

        private Error(E value) {
            this.value = value;
        }

        @Override
        public boolean isError() {
            return true;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public E getError() {
            return value;
        }

        @Override
        public S getSuccess() {
            return null;
        }
    }

    private static class Success<E extends github.fredsonchaves07.core.errors.Error,
            S extends ValueObject> extends Either<E, S> {
        private final S value;

        private Success(S value) {
            this.value = value;
        }

        @Override
        public boolean isError() {
            return false;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public S getSuccess() {
            throw new UnsupportedOperationException("getLeft() called on a Right value");
        }

        @Override
        public E getError() {
            return null;
        }
    }
}
