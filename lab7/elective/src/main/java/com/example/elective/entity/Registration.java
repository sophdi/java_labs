package com.example.elective.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Запис студента на факультативний курс.
 */
@Entity
@Table(name = "registrations",
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Registration() {}

    public Registration(LocalDate registrationDate, Student student, Course course) {
        this.registrationDate = registrationDate;
        this.student = student;
        this.course = course;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    @Override
    public String toString() {
        return "Registration{id=" + id + ", student=" + student + ", course=" + course + "}";
    }
}