package elective;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Фасад системи "Факультатив".
 * Приховує складну взаємодію між підсистемами Student, Course, Registration, Archive
 * і надає простий інтерфейс для основних операцій системи.
 */
public class ElectiveFacade {

    // внутрішні колекції підсистем
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Registration> registrations = new ArrayList<>();
    private List<Archive> archives = new ArrayList<>();

    private long regIdCounter = 1;
    private long archiveIdCounter = 1;

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Реєструє студента на курс.
     * Перевіряє існування студента і курсу, а також відсутність дублювання реєстрації.
     */
    public Registration registerStudentToCourse(long studentId, long courseId) {
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Помилка: студента з id=" + studentId + " не знайдено");
            return null;
        }

        Course course = findCourse(courseId);
        if (course == null) {
            System.out.println("Помилка: курс з id=" + courseId + " не знайдено");
            return null;
        }

        // перевірка на дублювання реєстрації
        for (Registration r : registrations) {
            if (r.getStudent().getId() == studentId && r.getCourse().getId() == courseId) {
                System.out.println("Студент вже зареєстрований на цей курс!");
                return r;
            }
        }

        Registration reg = new Registration(regIdCounter++, LocalDate.now(), student, course);
        registrations.add(reg);

        System.out.println("Студент " + student.getFirstName() + " " + student.getLastName()
                + " записаний на курс: " + course.getName());
        return reg;
    }

    /**
     * Виставляє оцінку студенту після завершення курсу і зберігає її в архіві.
     * Перевіряє коректність оцінки та відсутність дублювання.
     */
    public Archive gradeStudent(long studentId, long courseId, int grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("Помилка: оцінка повинна бути в межах від 0 до 100");
            return null;
        }

        Registration reg = findRegistration(studentId, courseId);
        if (reg == null) {
            System.out.println("Помилка: студент не зареєстрований на цей курс");
            return null;
        }

        // перевірка чи оцінка вже виставлена для цієї реєстрації
        for (Archive a : archives) {
            if (a.getRegistration().getId() == reg.getId()) {
                System.out.println("Оцінка вже виставлена для цієї реєстрації");
                return a;
            }
        }

        Archive archive = new Archive(archiveIdCounter++, grade, LocalDate.now(), reg);
        archives.add(archive);

        System.out.println("Викладач " + reg.getCourse().getTeacher().getLastName()
                + " виставив оцінку " + grade
                + " студенту " + reg.getStudent().getLastName()
                + " за курс: " + reg.getCourse().getName());
        return archive;
    }

    public void printAllRegistrations() {
        System.out.println("\n--- Всі реєстрації ---");
        if (registrations.isEmpty()) {
            System.out.println("Реєстрацій немає");
            return;
        }
        for (Registration r : registrations) {
            System.out.println("id=" + r.getId()
                    + " | Студент: " + r.getStudent().getLastName()
                    + " | Курс: " + r.getCourse().getName()
                    + " | Дата: " + r.getRegistrationDate());
        }
    }

    public void printArchive() {
        System.out.println("\n--- Архів оцінок ---");
        if (archives.isEmpty()) {
            System.out.println("Архів порожній");
            return;
        }
        for (Archive a : archives) {
            System.out.println("Студент: " + a.getRegistration().getStudent().getLastName()
                    + " | Курс: " + a.getRegistration().getCourse().getName()
                    + " | Оцінка: " + a.getGrade()
                    + " | Дата: " + a.getCompletionDate());
        }
    }

    // допоміжні методи пошуку — приховані від клієнта

    private Student findStudent(long id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private Course findCourse(long id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    private Registration findRegistration(long studentId, long courseId) {
        for (Registration r : registrations) {
            if (r.getStudent().getId() == studentId && r.getCourse().getId() == courseId) {
                return r;
            }
        }
        return null;
    }
}