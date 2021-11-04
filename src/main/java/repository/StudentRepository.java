package repository;

import javassist.NotFoundException;
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

    public StudentRepository(String persistenceUnitName){
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public StudentRepository(){
        this.emf = Persistence.createEntityManagerFactory("student-attendance");
    }

    @Override
    public Student save(Student newStudent) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(newStudent);
        em.getTransaction().commit();
        em.close();

        return newStudent;
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

    @Override
    public void delete(UUID id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Student student = em.find(Student.class, id);

        if(student == null) {
            throw new NotFoundException("Student with id: " + id + " not found");
        }

        em.remove(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("Student.deleteAll").executeUpdate();
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
