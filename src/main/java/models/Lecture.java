package models;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name="Lecture.findAll", query="SELECT l FROM Lecture l"),
        @NamedQuery(name="Lecture.deleteAll", query="DELETE FROM Lecture l")
})
public class Lecture {

    @Id
    private UUID id;
    private String date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Subject subject;

    @OneToMany
    @JoinColumn(name="student_id", referencedColumnName="id")
    private List<Student> attendees;

    @Transient
    private SimpleDateFormat formatter;

    public Lecture(Subject subject, Date date) {
        this.formatter = new SimpleDateFormat("dd.MM.yyyy");
        this.id = UUID.randomUUID();
        this.date = this.formatter.format(date);
        this.subject = subject;
        this.attendees = new ArrayList();
    }

    public Lecture() {}

    public void addAttendee(Student attendee) {
        if(!this.attendees.contains(attendee)) {
            this.attendees.add(attendee);
        }

    }

}
