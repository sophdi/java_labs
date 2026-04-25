package elective;

public class Course {

    private long id;
    private String name;
    private String description;
    private int durationWeeks;

    // асоціація: кожен курс має одного викладача
    private Teacher teacher;

    public Course() {
    }

    public Course(long id, String name, String description, int durationWeeks, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", durationWeeks=" + durationWeeks +
                ", teacher=" + teacher +
                '}';
    }
}