package junitTest.student;

import fakes.FakeStudentRepository;
import javassist.NotFoundException;
import models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.IStudentRepository;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentRepositoryTest {

    private static Student newStudent;
    private static IStudentRepository repository;
    private static Student student;
    private static Date date;

    @BeforeAll
    static void setUp() {
        repository = new FakeStudentRepository();
        date = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();
        newStudent = new Student("yrsa", "yrsa@school.dk", "20304050", "Strandvejen 1", "Hellerup", "2900", date);
    }

    @Test
    void testStudentBirthdateFormat() {
        newStudent = repository.save(newStudent);
        Optional<Student> student = repository.findById(newStudent.getId());
        Student otherStudent = student.get();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate= formatter.format(date);
        assertEquals(strDate, otherStudent.getBirthdate());
    }
}
