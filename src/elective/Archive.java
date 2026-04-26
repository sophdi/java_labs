package elective;

import java.time.LocalDate;

public class Archive {

    private long id;
    private int grade; // оцінка від 1 до 100
    private LocalDate completionDate; // дата виставлення оцінки

    // асоціація: архів зберігає дані про конкретну реєстрацію (студент + курс)
    private Registration registration;

    public Archive() {
    }

    public Archive(long id, int grade, LocalDate completionDate, Registration registration) {
        this.id = id;
        this.grade = grade;
        this.completionDate = completionDate;
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", grade=" + grade +
                ", completionDate=" + completionDate +
                ", registration=" + registration +
                '}';
    }
}