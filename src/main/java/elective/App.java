package elective;

import elective.config.Factory;
import elective.dao.*;
import elective.entity.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        TeacherDao teacherDao = Factory.getInstance().getTeacherRepository();
        CourseDao courseDao = Factory.getInstance().getCourseRepository();
        StudentDao studentDao = Factory.getInstance().getStudentRepository();
        RegistrationDao registrationDao = Factory.getInstance().getRegistrationRepository();
        ArchiveDao archiveDao = Factory.getInstance().getArchiveRepository();

        // очищення перед запуском
        archiveDao.deleteAll();
        registrationDao.deleteAll();
        studentDao.deleteAll();
        courseDao.deleteAll();
        teacherDao.deleteAll();

        // --- ВИКЛАДАЧІ ---
        Teacher teacher1 = new Teacher("Олена", "Коваленко", "kovalenko@university.ua");
        Teacher teacher2 = new Teacher("Микола", "Бондаренко", "bondarenko@university.ua");
        teacherDao.save(teacher1);
        teacherDao.save(teacher2);

        System.out.println("=== ВИКЛАДАЧІ ===");
        teacherDao.findAll().forEach(System.out::println);

        // --- КУРСИ (OneToMany) ---
        Course course1 = new Course("Java Programming", "Основи Java", 12, teacher1);
        Course course2 = new Course("Web Design", "HTML та CSS", 8, teacher2);
        Course course3 = new Course("Databases", "Реляційні БД та SQL", 10, teacher1);
        courseDao.save(course1);
        courseDao.save(course2);
        courseDao.save(course3);

        System.out.println("\n=== КУРСИ ===");
        courseDao.findAll().forEach(System.out::println);

        System.out.println("\n=== КУРСИ ВИКЛАДАЧА " + teacher1.getLastName() + " (OneToMany) ===");
        courseDao.findByTeacherId(teacher1.getId()).forEach(System.out::println);

        // --- СТУДЕНТИ (ManyToMany) ---
        Student student1 = new Student("Софія", "Дімітрова", "dimitrova@student.ua", 2);
        Student student2 = new Student("Марія", "Шевченко", "shevchenko@student.ua", 3);
        Student student3 = new Student("Іван", "Петренко", "petrenkoivan@student.ua", 1);
        Student student4 = new Student("Олена", "Кравченко", "kravchenko@student.ua", 3);
        student1.getCourses().add(course1);
        student1.getCourses().add(course3);
        student2.getCourses().add(course1);
        student3.getCourses().add(course2);
        student4.getCourses().add(course3);
        studentDao.save(student1);
        studentDao.save(student2);
        studentDao.save(student3);
        studentDao.save(student4);

        System.out.println("\n=== СТУДЕНТИ ===");
        studentDao.findAll().forEach(System.out::println);

        System.out.println("\n=== СТУДЕНТИ НА КУРСІ '" + course1.getName() + "' (ManyToMany) ===");
        studentDao.findByCourseId(course1.getId()).forEach(System.out::println);

        // --- РЕЄСТРАЦІЇ ---
        Registration reg1 = new Registration(LocalDate.now(), student1, course1);
        Registration reg2 = new Registration(LocalDate.now(), student1, course3);
        Registration reg3 = new Registration(LocalDate.now(), student2, course1);
        registrationDao.save(reg1);
        registrationDao.save(reg2);
        registrationDao.save(reg3);

        System.out.println("\n=== РЕЄСТРАЦІЇ ===");
        registrationDao.findAll().forEach(System.out::println);

        // --- АРХІВ (OneToOne) ---
        Archive archive1 = new Archive(95, LocalDate.now(), reg1);
        Archive archive2 = new Archive(80, LocalDate.now(), reg2);
        Archive archive3 = new Archive(88, LocalDate.now(), reg3);
        archiveDao.save(archive1);
        archiveDao.save(archive2);
        archiveDao.save(archive3);

        System.out.println("\n=== АРХІВ ОЦІНОК (OneToOne) ===");
        archiveDao.findAll().forEach(a ->
            System.out.println(a.getRegistration().getStudent().getLastName()
                + " | " + a.getRegistration().getCourse().getName()
                + " | оцінка: " + a.getGrade())
        );

        Factory.getInstance().getEntityManagerFactory().close();
    }
}
