package fakes;

import models.Lecture;
import models.Student;
import repository.ILectureRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeLectureRepository implements ILectureRepository {

    private List<Lecture> lectures = new ArrayList();
}
