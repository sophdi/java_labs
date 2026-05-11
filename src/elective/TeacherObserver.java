package elective;

// конкретний спостерігач — викладач отримує сповіщення коли студент записується на його курс
public class TeacherObserver implements CourseObserver {

    private final Teacher teacher;

    public TeacherObserver(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void onStudentRegistered(Student student, Course course) {
        // реагуємо лише якщо курс належить цьому викладачу
        if (course.getTeacher().getId() == teacher.getId()) {
            System.out.println("[Сповіщення] Викладач " + teacher.getLastName()
                    + " отримав сповіщення: студент " + student.getFirstName()
                    + " " + student.getLastName()
                    + " записався на курс \"" + course.getName() + "\"");
        }
    }
}