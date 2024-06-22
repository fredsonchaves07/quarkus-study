package github.fredsonchaves07.domain.forum.entities.student;

import github.fredsonchaves07.core.entities.Entity;

public class Student extends Entity<StudentID> {

    private String name;

    private Student(String name) {
        super(new StudentID());
        this.name = name;
    }

    private Student(StudentID id, String name) {
        super(id);
        this.name = name;
    }

    public Student createStudent(String name) {
        return new Student(name);
    }

    public Student createStudent(StudentID id, String name) {
        return new Student(id, name);
    }
}
