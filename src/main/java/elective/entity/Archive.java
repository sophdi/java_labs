package elective.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "archives")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int grade;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @OneToOne
    @JoinColumn(name = "registration_id", nullable = false, unique = true)
    private Registration registration;

    public Archive() {}

    public Archive(int grade, LocalDate completionDate, Registration registration) {
        this.grade = grade;
        this.completionDate = completionDate;
        this.registration = registration;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

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
