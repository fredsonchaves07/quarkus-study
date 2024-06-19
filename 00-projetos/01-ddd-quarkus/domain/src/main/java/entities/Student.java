package entities;

import java.util.UUID;

public class Student {

    private String id;

    private String name;

    private Student(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    private Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student createStudent(String name) {
        return new Student(name);
    }

    public Student createStudent(String id, String name) {
        return new Student(id, name);
    }
}
