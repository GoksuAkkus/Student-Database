package com.example.kontess.studentdatabase;

/**
 * Created by Kontess on 6.5.2018.
 */

public class Student {

    public Student(String name, String surname, Integer age, Double gpa, String gender, String clas) {
        Name = name;
        Surname = surname;
        Age = age;
        Gpa = gpa;
        Gender = gender;
        Clas = clas;

    }

    public Student(String name, String surname, Integer age, Double gpa, String gender, String clas,Integer id) {
        Name = name;
        Surname = surname;
        Age = age;
        Gpa = gpa;
        Gender = gender;
        Clas = clas;
        Id=id;

    }


    private Integer Id;
    private String Name;
    private String Surname;
    private Integer Age;
    private Double Gpa;
    private String Gender;
    private String Clas;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Double getGpa() {
        return Gpa;
    }

    public void setGpa(Double gpa) {
        Gpa = gpa;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getClas() {
        return Clas;
    }

    public void setClas(String clas) {
        Clas = clas;
    }

    public Integer getId() { return Id; }

    public void setId(Integer id) { Id = id; }




}
