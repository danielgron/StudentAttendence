package models;

import lombok.Data;


import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t"),
        @NamedQuery(name="Teacher.deleteAll", query="DELETE FROM Teacher t")
})
public class Teacher {

    @Id
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Subject> subjects;

    public Teacher(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.subjects = new ArrayList();
    }

    public Teacher() {}

    public void addSubject(Subject subject) {
        if(!this.subjects.contains(subject)){
            this.subjects.add(subject);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
