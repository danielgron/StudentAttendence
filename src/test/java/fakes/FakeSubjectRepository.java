package fakes;

import models.Subject;
import repository.ISubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeSubjectRepository implements ISubjectRepository {
    private List<Subject> subjects = new ArrayList();

    @Override
    public Subject save(Subject subject) {
        if(!subjects.contains(subject))
            subjects.add(subject);
        return subject;
    }

    @Override
    public Optional<Subject> findById(UUID id) {
        Optional<Subject> opSubject = Optional.empty();

        for (Subject subject: subjects) {
            if(subject.getId().equals(id)) {
                opSubject = Optional.of(subject);
            }
        }
        return opSubject;
    }

    @Override
    public void deleteAll() {
        subjects = new ArrayList<>();
    }

    @Override
    public List<Subject> findAll() {
        return subjects;
    }
}
