package com.example.elective.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Архівний запис з оцінкою після завершення курсу.
 */
@Entity
@Table(name = "archives")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Оцінка від 0 до 100")
    @Max(value = 100, message = "Оцінка від 0 до 100")
    @Column(nullable = false)
    private int grade;

    @Column(name = "completion_date", nullable = false)
    private LocalDate completionDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "registration_id", nullable = false, unique = true)
    private Registration registration;

    public Archive() {}

    public Archive(int grade, LocalDate completionDate, Registration registration) {
        this.grade = grade;
        this.completionDate = completionDate;
        this.registration = registration;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public LocalDate getCompletionDate() { return completionDate; }
    public void setCompletionDate(LocalDate completionDate) { this.completionDate = completionDate; }

    public Registration getRegistration() { return registration; }
    public void setRegistration(Registration registration) { this.registration = registration; }

    @Override
    public String toString() {
        return "Archive{id=" + id + ", grade=" + grade + ", completionDate=" + completionDate + "}";
    }
}