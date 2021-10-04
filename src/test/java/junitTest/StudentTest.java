package junitTest;

import models.Lecture;
import models.Student;
import models.Subject;
import models.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private static Student student;
    private static Teacher teacher;
    private static Subject subject;
    private static Date date;

    @BeforeAll
    static void setUp() {
        student = new Student("Benjamin");
        teacher = new Teacher("Lars");
        subject = new Subject("System Integration", teacher);
        date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    }

    @Test
    void testCreateStudent() {
        Student student = new Student("Benjamin");
        assertTrue(student != null);
    }

    @Test
    void testGetStudentName() {
        String name = student.getName();
        assertEquals("Benjamin", name);
    }

    @Test
    void testStudentGetEmptySubjects() {
        Student student = new Student("Amalie");
        List<Subject> subjects = student.getSubjects();
        assertTrue(subjects.isEmpty());
    }

    @Test
    void testStudentAddSubject() {
        student.addSubject(subject);
        List<Subject> subjects = student.getSubjects();
        assertTrue(subjects.contains(subject));
    }

    @Test
    void testSubjectHasStudent() {
        student.addSubject(subject);
        assertTrue(subject.getStudents().contains(student));
    }

    @Test
    void testStudentOnlyAddedOnce() {
        student.addSubject(subject);
        student.addSubject(subject);
        List<Student> students = subject.getStudents().stream().filter(s -> s.getId() == student.getId()).collect(Collectors.toList());
        assertTrue(students.size() == 1);
    }

    @Test
    void testSubjectOnlyAddedOnce() {
        subject.addStudent(student);
        subject.addStudent(student);
        List<Subject> subjects = student.getSubjects().stream().filter(s -> s.getId() == subject.getId()).collect(Collectors.toList());
        assertTrue(subjects.size() == 1);
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

    @Test
    void testAddLectureToSubject() {
        subject.addLecture(date);
        List<Lecture> lectures = subject.getLectures().stream().filter(l -> l.getDate() == date).collect(Collectors.toList());
        assertTrue(lectures.size() == 1);
    }

    @Test
    void testAddAttendeeToLecture() {
        subject.addLecture(date);
        Lecture lecture = subject.getLecture(date);
        lecture.addAttendee(student);
        assertTrue(lecture.getAttendees().contains(student));
    }






}
