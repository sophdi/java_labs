package com.example.elective.pattern.builder;

import com.example.elective.entity.Student;

/**
 * Будівник студента (GoF Builder).
 * Використовується для поетапного конструювання об'єкта Student.
 */
public class StudentBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private int courseYear;

    private StudentBuilder() {}

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder courseYear(int courseYear) {
        this.courseYear = courseYear;
        return this;
    }

    public Student build() {
        if (firstName == null || lastName == null || email == null) {
            throw new IllegalStateException("firstName, lastName та email обов'язкові");
        }
        return new Student(firstName, lastName, email, courseYear);
    }
}