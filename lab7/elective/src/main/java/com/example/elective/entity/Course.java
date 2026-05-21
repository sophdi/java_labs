package com.example.elective.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Факультативний курс, закріплений за одним викладачем.
 */
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Назва курсу обов'язкова")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 1, message = "Тривалість мінімум 1 тиждень")
    @Column(name = "duration_weeks")
    private int durationWeeks;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Course() {}

    public Course(String name, String description, int durationWeeks, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.teacher = teacher;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(int durationWeeks) { this.durationWeeks = durationWeeks; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "', durationWeeks=" + durationWeeks + "}";
    }
}