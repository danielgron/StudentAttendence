package repository;

import models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class StudentRepository implements IStudentRepository {

    private EntityManagerFactory emf;

    public StudentRepository(){
        this.emf = Persistence.createEntityManagerFactory("student-attendance");
    }

    @Override
    public Student save(Student newStudent) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Student student = new Student(newStudent);
        em.persist(student);
        em.getTransaction().commit();
        em.close();

        return student;
    }

    @Override
    public Optional<Student> findById(UUID id) {
        EntityManager em = emf.createEntityManager();

        Student student = em.find(Student.class, id);
        return student != null ? Optional.of(student) : Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();

        List<Student> students = em.createNamedQuery("Student.findAll", Student.class).getResultList();
        return students;
    }
}