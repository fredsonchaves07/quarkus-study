package entities.instructor;

import core.entities.Entity;

public class Instructor extends Entity<InstructorID> {

    private String name;

    private Instructor(String name) {
        super(new InstructorID());
        this.name = name;
    }

    private Instructor(InstructorID id, String name) {
        super(id);
        this.name = name;
    }

    public Instructor createStudent(String name) {
        return new Instructor(name);
    }

    public Instructor createStudent(InstructorID id, String name) {
        return new Instructor(id, name);
    }
}
