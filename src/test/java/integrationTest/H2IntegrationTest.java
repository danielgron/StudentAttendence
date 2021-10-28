package integrationTest;

import javassist.NotFoundException;
import org.junit.After;
import org.junit.jupiter.api.*;
import models.Student;
import repository.IStudentRepository;
import repository.StudentRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class H2IntegrationTest {
    private static Student newStudent;
    private static Date date;
    private static IStudentRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new StudentRepository();
        date = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();
        newStudent = new Student("yrsa", "yrsa@school.dk", "20304050", "Strandvejen 1", "Hellerup", "2900", date);
    }

    @AfterAll
    static void tearDown() throws NotFoundException {
        repository.delete(newStudent.getId());
    }

    @Test
    void testH2DatabaseIsActive() {
        List<Student> students = repository.findAll();
        assertTrue(students.isEmpty());
    }

    @Test
    void testH2CreateStudent() {
        Student student = repository.save(newStudent);
        Optional<Student> savedStudent = repository.findById(student.getId());
        assertTrue(savedStudent.isPresent());
        assertEquals(student.getId(),savedStudent.get().getId());
    }
}