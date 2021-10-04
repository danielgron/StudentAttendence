package models;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Subject {

    private UUID id;
    private List<Student> students;
    private String name;
    private Teacher teacher;
    private List<Lecture> lectures;

    public Subject(String name, Teacher teacher) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.students = new ArrayList();
        this.teacher = teacher;
        this.lectures = new ArrayList<>();
    }

    public void addStudent(Student student) {

        if(!this.students.contains(student)){
            this.students.add(student);
        }

    }

    public void addLecture(Date date) {
        if(this.getLecture(date) == null) {
            this.lectures.add(new Lecture(this, date));
        }
    }

    public Lecture getLecture(Date date) {
        for (Lecture lecture: lectures) {
            if(lecture.getDate().equals(date)) {
                return lecture;
            }
        }
        return null;
    }


}
