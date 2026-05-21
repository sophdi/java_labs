package elective.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Registration() {}

    public Registration(LocalDate registrationDate, Student student, Course course) {
        this.registrationDate = registrationDate;
        this.student = student;
        this.course = course;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    @Override
    public String toString() {
        return "Registration{id=" + id + ", registrationDate=" + registrationDate + ", student=" + student + ", course=" + course + "}";
    }
}
