package github.fredsonchaves07.db;

import java.util.ArrayList;
import java.util.List;

public class MemoryDB<T> implements DB<T> {

    private List<T> values = new ArrayList<>();

    private MemoryDB() {

    }

    public static <T> DB<T> createDB() {
        return new MemoryDB<>();
    }

    @Override
    public void push(T value) {
        values.add(value);
    }

    @Override
    public void drop() {

    }
}
