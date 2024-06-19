package entities;

import java.util.UUID;

public class Instructor {

    private String id;

    private String name;

    private Instructor(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    private Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Instructor createStudent(String name) {
        return new Instructor(name);
    }

    public Instructor createStudent(String id, String name) {
        return new Instructor(id, name);
    }
}
