package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Lecture {

    private UUID id;
    private Date date;
    private Subject subject;
    private List<Student> attendees;

    public Lecture(Subject subject, Date date) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.subject = subject;
        this.attendees = new ArrayList();
    }

    public void addAttendee(Student attendee) {
        if(!this.attendees.contains(attendee)) {
            this.attendees.add(attendee);
        }

    }

}
