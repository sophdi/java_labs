package com.example.elective.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Сутність студента в системі факультативу.
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я обов'язкове")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Прізвище обов'язкове")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email обов'язковий")
    @Email(message = "Невірний формат email")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 1, message = "Курс від 1 до 6")
    @Max(value = 6, message = "Курс від 1 до 6")
    @Column(name = "course_year")
    private int courseYear;

    public Student() {}

    public Student(String firstName, String lastName, String email, int courseYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseYear = courseYear;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getCourseYear() { return courseYear; }
    public void setCourseYear(int courseYear) { this.courseYear = courseYear; }

    /** Повне ім'я студента. */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + firstName + " " + lastName + "', email='" + email + "'}";
    }
}