package integrationTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import models.Student;
import org.junit.jupiter.api.BeforeAll;
import repository.IStudentRepository;
import repository.StudentRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
        repository.save(newStudent);
    }

    @Test
    void testH2DatabaseIsActive() {
        List<Student> students = repository.findAll();
        assertTrue(!students.isEmpty());
    }
}