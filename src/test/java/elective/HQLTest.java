package elective;

import elective.config.Factory;
import elective.dao.*;
import elective.entity.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HQLTest {

    private static TeacherDao teacherDao;
    private static CourseDao courseDao;
    private static StudentDao studentDao;

    @BeforeClass
    public static void setUp() {
        teacherDao = Factory.getInstance().getTeacherRepository();
        courseDao = Factory.getInstance().getCourseRepository();
        studentDao = Factory.getInstance().getStudentRepository();

        Teacher teacher = new Teacher("Андрій", "Савченко", "savchenko_hql@university.ua");
        teacherDao.save(teacher);

        courseDao.save(new Course("HQL Course", "Курс з HQL", 15, teacher));
        courseDao.save(new Course("Short Course", "Короткий курс", 3, teacher));

        studentDao.save(new Student("Тест", "HQL", "hql_test@student.ua", 2));
    }

    @Test
    public void testFindAllTeachersOrderedHQL() {
        List<Teacher> teachers = teacherDao.findAllOrderedHQL();
        assertFalse(teachers.isEmpty());
    }

    @Test
    public void testFindTeacherByFirstNameHQL() {
        List<Teacher> teachers = teacherDao.findByFirstNameHQL("Андрій");
        assertFalse(teachers.isEmpty());
        assertEquals("Андрій", teachers.get(0).getFirstName());
    }

    @Test
    public void testFindAllCoursesOrderedHQL() {
        List<Course> courses = courseDao.findAllOrderedHQL();
        assertFalse(courses.isEmpty());
    }

    @Test
    public void testFindCoursesLongerThanHQL() {
        List<Course> courses = courseDao.findLongerThanHQL(10);
        assertFalse(courses.isEmpty());
        courses.forEach(c -> assertTrue(c.getDurationWeeks() > 10));
    }

    @Test
    public void testFindAllStudentsOrderedHQL() {
        List<Student> students = studentDao.findAllOrderedHQL();
        assertFalse(students.isEmpty());
    }

    @Test
    public void testFindStudentsByCourseYearHQL() {
        List<Student> students = studentDao.findByCourseYearHQL(2);
        assertFalse(students.isEmpty());
        students.forEach(s -> assertEquals(2, s.getCourseYear()));
    }
}
