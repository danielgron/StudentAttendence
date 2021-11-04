package repository;

import models.Student;
import models.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SubjectRepository implements ISubjectRepository {

    private EntityManagerFactory emf;

    public SubjectRepository(String persistenceUnitName) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public SubjectRepository() {
        this.emf = Persistence.createEntityManagerFactory("student-attendance");
    }

    @Override
    public Subject save(Subject subject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
        em.close();

        return subject;
    }

    @Override
    public Optional<Subject> findById(UUID subjectId) {
        EntityManager em = emf.createEntityManager();

        Subject subject = em.find(Subject.class, subjectId);
        return subject != null ? Optional.of(subject) : Optional.empty();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Subject> findAll() {
        return null;
    }
}
