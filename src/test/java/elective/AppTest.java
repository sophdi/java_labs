package elective;

import elective.config.Factory;
import elective.dao.*;
import elective.entity.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    private static final TeacherDao teacherDao = Factory.getInstance().getTeacherRepository();
    private static final CourseDao courseDao = Factory.getInstance().getCourseRepository();
    private static final StudentDao studentDao = Factory.getInstance().getStudentRepository();
    private static final RegistrationDao registrationDao = Factory.getInstance().getRegistrationRepository();
    private static final ArchiveDao archiveDao = Factory.getInstance().getArchiveRepository();

    @Test
    public void testOneToMany_TeacherWithCourses() {
        Teacher teacher = new Teacher("Олена", "Коваль", "koval@university.ua");
        teacherDao.save(teacher);

        courseDao.save(new Course("Java Basics", "Основи Java", 8, teacher));
        courseDao.save(new Course("Spring Boot", "Розробка на Spring", 10, teacher));

        List<Course> courses = courseDao.findByTeacherId(teacher.getId());
        assertEquals(2, courses.size());
    }

    @Test
    public void testManyToMany_StudentCourses() {
        Teacher teacher = new Teacher("Іван", "Петренко", "petrenko@university.ua");
        teacherDao.save(teacher);

        Course course = new Course("Algorithms", "Алгоритми", 12, teacher);
        courseDao.save(course);

        Student s1 = new Student("Анна", "Бондаренко", "anna@student.ua", 2);
        Student s2 = new Student("Олег", "Мельник", "oleg@student.ua", 3);
        s1.getCourses().add(course);
        s2.getCourses().add(course);
        studentDao.save(s1);
        studentDao.save(s2);

        assertEquals(2, studentDao.findByCourseId(course.getId()).size());
    }

    @Test
    public void testOneToOne_RegistrationArchive() {
        Teacher teacher = new Teacher("Марія", "Шевченко", "shevchenko@university.ua");
        teacherDao.save(teacher);

        Course course = new Course("Databases", "Бази даних", 6, teacher);
        courseDao.save(course);

        Student student = new Student("Соня", "Дем'яненко", "sonya@student.ua", 1);
        studentDao.save(student);

        Registration reg = new Registration(LocalDate.now(), student, course);
        registrationDao.save(reg);

        Archive archive = new Archive(95, LocalDate.now(), reg);
        archiveDao.save(archive);

        Archive found = archiveDao.findByRegistrationId(reg.getId());
        assertNotNull(found);
        assertEquals(95, found.getGrade());
    }

    @Test
    public void testSaveAndFindStudent() {
        Student student = new Student("Тест", "Студент", "test@student.ua", 1);
        studentDao.save(student);

        Student found = studentDao.findById(student.getId());
        assertNotNull(found);
        assertEquals("Тест", found.getFirstName());
    }

    @Test
    public void testFindAllTeachers() {
        teacherDao.save(new Teacher("Новий", "Викладач", "new@university.ua"));
        assertFalse(teacherDao.findAll().isEmpty());
    }
}
