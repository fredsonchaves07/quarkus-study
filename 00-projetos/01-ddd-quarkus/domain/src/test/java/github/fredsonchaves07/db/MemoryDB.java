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
    public List<T> listAll() {
        return new ArrayList<>(values);
    }

    @Override
    public void replace(T oldValue, T newValue) {
        if (values.contains(oldValue)) {
            values.remove(oldValue);
            values.add(newValue);
        }
    }

    @Override
    public void delete(T value) {
        values.remove(value);
    }
}
