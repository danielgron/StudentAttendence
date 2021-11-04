package models;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s"),
        @NamedQuery(name="Subject.deleteAll", query="DELETE FROM Subject s")
})
public class Subject {

    @Id
    private UUID id;
    private String name;

    @JoinTable(name = "subject_student", joinColumns = {
            @JoinColumn(name = "subject_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Student> students;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Lecture> lectures;

    @Transient
    private SimpleDateFormat formatter;

    public Subject(String name, Teacher teacher) {
        this.formatter = new SimpleDateFormat("dd.MM.yyyy");
        this.id = UUID.randomUUID();
        this.name = name;
        this.students = new ArrayList();
        this.teacher = teacher;
        this.lectures = new ArrayList<>();
    }

    public Subject() {}

    public void addStudent(Student student) {

        if(!this.students.contains(student)){
            this.students.add(student);
        }

    }

    public Lecture addLecture(Date date) {
        Lecture lecture = null;
        if(this.getLecture(date) == null) {
            lecture = new Lecture(this, date);
            this.lectures.add(lecture);
        }
        return lecture;
    }

    public Lecture getLecture(Date date) {
        String dateAsString = formatter.format(date);
        for (Lecture lecture: lectures) {
            if(lecture.getDate().equals(dateAsString)) {
                return lecture;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
