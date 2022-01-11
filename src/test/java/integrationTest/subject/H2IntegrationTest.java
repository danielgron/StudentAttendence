package integrationTest.subject;

import javassist.NotFoundException;
import models.Student;
import models.Subject;
import models.Teacher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IStudentRepository;
import repository.ISubjectRepository;
import repository.StudentRepository;
import repository.SubjectRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class H2IntegrationTest {
    private static Subject subject;
    private static Subject newSubject;
    private static Date date;
    private static ISubjectRepository repository;

    @BeforeAll
    static void setUp() {
        String persistenceUnitName = "student-attendance-test";
        repository = new SubjectRepository(persistenceUnitName);

        Date birthDate = new GregorianCalendar(1995, Calendar.APRIL, 8).getTime();
        Teacher teacher = new Teacher("Lis");
        newSubject = new Subject("Undersøgelse og Formidling", teacher);

    }

    @BeforeEach
    void setupEach(){
        Teacher teacher = new Teacher("Lis");
        newSubject = new Subject("Undersøgelse og Formidling", teacher);
    }

    @AfterAll
    static void tearDown() throws NotFoundException {
        repository.deleteAll();
    }

    /* Not implemented
    @Test
    void testH2DatabaseIsActive() {
        List<Subject> subjects = repository.findAll();
        assertTrue(subjects.size() >= 0);
    }

     */

    @Test
    void testCreateSubject() {
        Subject subject = repository.save(newSubject);
        Optional<Subject> savedSubject = repository.findById(subject.getId());
        assertTrue(savedSubject.isPresent());
        assertEquals(subject.getId(),savedSubject.get().getId());
    }

    @Test
    void testFindById() throws NotFoundException {
        Subject subject = repository.save(newSubject);
        Optional<Subject> opSubject = repository.findById(subject.getId());
        assertTrue(opSubject.isPresent());
    }

/*    @Test
    void testFindAllSubjects() throws NotFoundException {
        List<Subject> subjects = repository.findAll();
        assertTrue(!subjects.isEmpty());
    }*/

}