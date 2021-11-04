package junitTest.subject;

import models.Lecture;
import models.Student;
import models.Subject;
import models.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectTest {
    private static Student student;
    private static Teacher teacher;
    private static Subject subject;
    private static Date date;
    private static SimpleDateFormat formatter;

    @BeforeAll
    static void setUp() {
        student = new Student("Benjamin");
        teacher = new Teacher("Lars");
        subject = new Subject("System Integration", teacher);
        date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        formatter = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Test
    void testAddLectureToSubject() {
        subject.addLecture(date);
        List<Lecture> lectures = subject.getLectures().stream().filter(l -> l.getDate().equals(formatter.format(date))).collect(Collectors.toList());
        assertEquals(1,lectures.size());
    }

    @Test
    void testAddLectureSameDateNotAddedTwice() {
        subject.addLecture(date);
        subject.addLecture(date);
        List<Lecture> lectures = subject.getLectures().stream().filter(l -> l.getDate().equals(formatter.format(date))).collect(Collectors.toList());
        assertEquals(1,lectures.size());
    }

    @Test
    void testSubjectGetLecture() {
        subject.addLecture(date);
        Lecture lecture = subject.getLecture(date);
        assertEquals(formatter.format(date), lecture.getDate());
        assertEquals(subject, lecture.getSubject());
    }

    @Test
    void testAddAttendeeToLecture() {
        subject.addLecture(date);
        Lecture lecture = subject.getLecture(date);
        lecture.addAttendee(student);
        assertTrue(lecture.getAttendees().contains(student));
    }

    @Test
    void testGetAttendeesFromLecture() {
        subject.addLecture(date);
        Lecture lecture = subject.getLecture(date);
        lecture.addAttendee(student);

        assertEquals(1,lecture.getAttendees().size());
    }

}
