package github.fredsonchaves07.db;

public interface DB<T> {

    void push(T value);

    void drop();
}
