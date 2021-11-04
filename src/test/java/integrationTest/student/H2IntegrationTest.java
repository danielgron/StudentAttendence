package integrationTest.student;

import fakes.FakeStudentRepository;
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
    private static Student student;
    private static Student newStudent;
    private static Date date;
    private static IStudentRepository repository;

    @BeforeAll
    static void setUp() {
        String persistenceUnitName = "student-attendance-test";
        repository = new StudentRepository(persistenceUnitName);

        Date birthDate = new GregorianCalendar(1995, Calendar.APRIL, 8).getTime();
        student = new Student("Amanda", "amanda@school.dk", "42441486","Ålandsgade 49", "København", "1000", birthDate);
        student = repository.save(student);

        date = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();
        newStudent = new Student("yrsa", "yrsa@school.dk", "20304050", "Strandvejen 1", "Hellerup", "2900", date);
    }

    @AfterAll
    static void tearDown() throws NotFoundException {
        repository.deleteAll();
    }

    @Test
    void testH2DatabaseIsActive() {
        List<Student> students = repository.findAll();
        assertTrue(students.size() >= 0);
    }

    @Test
    void testCreateStudent() {
        Student student = repository.save(newStudent);
        Optional<Student> savedStudent = repository.findById(student.getId());
        assertTrue(savedStudent.isPresent());
        assertEquals(student.getId(),savedStudent.get().getId());
    }

    @Test
    void testFindAllStudents() throws NotFoundException {
        List<Student> students = repository.findAll();
        assertTrue(!students.isEmpty());
    }

    @Test
    void testDeleteStudent() throws NotFoundException {
        repository.delete(student.getId());
        Optional<Student> deletedStudent = repository.findById(student.getId());
        assertTrue(!deletedStudent.isPresent());
    }
}