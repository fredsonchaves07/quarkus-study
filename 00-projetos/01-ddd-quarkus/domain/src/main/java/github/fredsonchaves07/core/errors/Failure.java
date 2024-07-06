package github.fredsonchaves07.core.errors;

public class Failure<T> {

    private T value;

    private Failure(T value) {
        this.value = value;
    }
}
