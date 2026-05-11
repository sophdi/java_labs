package elective;

// інтерфейс для всіх спостерігачів системи факультативу
public interface CourseObserver {
    void onStudentRegistered(Student student, Course course);
}