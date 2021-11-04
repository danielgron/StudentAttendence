package junitTest.teacher;

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

public class TeacherTest {
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
    void testCreateTeacher() {
        Teacher teacher = new Teacher("Lars");
        assertTrue(teacher != null);
    }

    @Test
    void testGetTeacherName() {
        String name = teacher.getName();
        assertEquals("Lars", name);
    }

    @Test
    void testTeacherGetEmptySubjects() {
        Teacher teacher = new Teacher("Dora");
        List<Subject> subjects = teacher.getSubjects();
        assertTrue(subjects.isEmpty());
    }

    @Test
    void testTeacherAddSubject() {
        teacher.addSubject(subject);
        List<Subject> subjects = teacher.getSubjects();
        assertTrue(subjects.contains(subject));
    }

    @Test
    void testSubjectOnlyAddedOnceForTeacher() {
        teacher.addSubject(subject);
        teacher.addSubject(subject);
        List<Subject> subjects = teacher.getSubjects().stream().filter(s -> s.getId() == subject.getId()).collect(Collectors.toList());
        assertTrue(subjects.size() == 1);
    }

}
