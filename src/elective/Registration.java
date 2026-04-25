package elective;

import java.time.LocalDate;

public class Registration {

    private long id;
    private LocalDate registrationDate;

    // асоціація: реєстрація прив'язана до студента і курсу
    private Student student;
    private Course course;

    public Registration() {
    }

    public Registration(long id, LocalDate registrationDate, Student student, Course course) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.student = student;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", registrationDate=" + registrationDate +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}