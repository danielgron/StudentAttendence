package junitTest;

import fakes.FakeStudentRepository;
import models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.IStudentRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    private static Student newStudent;
    private static IStudentRepository repository;
    private static Date date;

    @BeforeAll
    static void setUp() {

        repository = new FakeStudentRepository();

        date = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();
        newStudent = new Student("yrsa", "yrsa@school.dk", "20304050", "Strandvejen 1", "Hellerup", "2900", date);
    }

    @Test
    void createStudent() {
        newStudent = repository.save(newStudent);
        Optional<Student> student = repository.findById(newStudent.getId());
        assertTrue(student.isPresent());
    }
}
