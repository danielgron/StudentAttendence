package models;

import lombok.Data;


import java.util.*;

@Data
public class Teacher {

    private UUID id;
    private String name;
    private List<Subject> subjects;

    public Teacher(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.subjects = new ArrayList();
    }

    public void addSubject(Subject subject) {
        if(!this.subjects.contains(subject)){
            this.subjects.add(subject);
        }
    }

}