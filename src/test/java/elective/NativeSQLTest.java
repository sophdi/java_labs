package elective;

import elective.config.Factory;
import elective.dao.*;
import elective.entity.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class NativeSQLTest {

    private static TeacherDao teacherDao;
    private static CourseDao courseDao;
    private static StudentDao studentDao;

    @BeforeClass
    public static void setUp() {
        teacherDao = Factory.getInstance().getTeacherRepository();
        courseDao = Factory.getInstance().getCourseRepository();
        studentDao = Factory.getInstance().getStudentRepository();

        Teacher teacher = new Teacher("Олена", "Коваль", "koval_sql@university.ua");
        teacherDao.save(teacher);

        courseDao.save(new Course("Java SQL", "Java з SQL", 8, teacher));

        Student student = new Student("Тест", "SQL", "sql_test@student.ua", 2);
        studentDao.save(student);
    }

    @Test
    public void testFindAllTeachersNativeSQL() {
        List<Teacher> teachers = teacherDao.findAllNativeSQL();
        assertFalse(teachers.isEmpty());
    }

    @Test
    public void testFindByEmailNativeSQL() {
        List<Teacher> teachers = teacherDao.findByEmailNativeSQL("koval_sql@university.ua");
        assertEquals(1, teachers.size());
        assertEquals("Коваль", teachers.get(0).getLastName());
    }

    @Test
    public void testFindAllCoursesNativeSQL() {
        List<Course> courses = courseDao.findAllNativeSQL();
        assertFalse(courses.isEmpty());
    }

    @Test
    public void testFindCourseByNameNativeSQL() {
        List<Course> courses = courseDao.findByNameNativeSQL("Java SQL");
        assertEquals(1, courses.size());
    }

    @Test
    public void testFindAllStudentsNativeSQL() {
        List<Student> students = studentDao.findAllNativeSQL();
        assertFalse(students.isEmpty());
    }

    @Test
    public void testFindStudentByCourseYearNativeSQL() {
        List<Student> students = studentDao.findByCourseYearNativeSQL(2);
        assertFalse(students.isEmpty());
    }
}