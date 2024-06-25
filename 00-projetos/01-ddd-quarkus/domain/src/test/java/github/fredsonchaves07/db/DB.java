package github.fredsonchaves07.db;

import java.util.List;

public interface DB<T> {

    void push(T value);

    List<T> listAll();

    void delete(T value);
}
