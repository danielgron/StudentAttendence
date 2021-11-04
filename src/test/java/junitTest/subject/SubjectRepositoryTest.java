package junitTest.subject;

import fakes.FakeStudentRepository;
import fakes.FakeSubjectRepository;
import models.Lecture;
import models.Student;
import models.Subject;
import models.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.IStudentRepository;
import repository.ISubjectRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectRepositoryTest {
    private static Student newStudent;
    private static ISubjectRepository repository;
    private static Subject subject;
    private static Date date;
    private static Lecture lecture;

    @BeforeAll
    static void setUp() {
        repository = new FakeSubjectRepository();
        date = new GregorianCalendar(1995, Calendar.JUNE, 4).getTime();
        Teacher teacher = new Teacher("Bo");
        subject = new Subject("Software Testing", teacher);
    }

    @Test
    void testLectureDateFormat() {
        lecture = subject.addLecture(date);
        subject = repository.save(subject);
        Optional<Subject> opSubject = repository.findById(subject.getId());
        Subject otherSubject = opSubject.get();
        lecture = otherSubject.getLecture(date);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate= formatter.format(date);
        assertEquals(strDate, lecture.getDate());
    }
}
