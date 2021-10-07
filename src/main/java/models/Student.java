package models;

import lombok.Data;


import java.util.*;

@Data
public class Student {

    private UUID id;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String city;
    private String zipcode;
    private String birthdate;
    private List<Subject> subjects;

    public Student(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.subjects = new ArrayList();
    }

    public Student(String name, String email, String phonenumber, String address, String city, String zipcode, Date birthdate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.birthdate = birthdate.toString();

    }

    public void addSubject(Subject subject) {
        if(!this.subjects.contains(subject)) {
            this.subjects.add(subject);
            subject.addStudent(this);
        }

    }



}
