package models;

import lombok.Data;


import java.util.*;

@Data
public class Student {

    private UUID id;
    private String name;
    private List<Subject> subjects;

    public Student(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.subjects = new ArrayList();
    }

    public void addSubject(Subject subject) {
        if(!this.subjects.contains(subject)) {
            this.subjects.add(subject);
            subject.addStudent(this);
        }

    }



}
