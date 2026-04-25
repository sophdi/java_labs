package elective;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // створюємо викладачів
        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setFirstName("Олена");
        teacher1.setLastName("Коваленко");
        teacher1.setEmail("kovalenko@university.ua");

        Teacher teacher2 = new Teacher(2L, "Микола", "Бондаренко", "bondarenko@university.ua");

        System.out.println("ВИКЛАДАЧІ");
        System.out.println(teacher1);
        System.out.println(teacher2);

        // створюємо курси (кожен курс - один викладач)
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Java Programming");
        course1.setDescription("Основи програмування на Java");
        course1.setDurationWeeks(12);
        course1.setTeacher(teacher1); // прив'язуємо викладача до курсу

        Course course2 = new Course(2L, "Web Design", "Основи веб-дизайну та HTML/CSS", 8, teacher2);

        Course course3 = new Course(3L, "Databases", "Реляційні бази даних та SQL", 10, teacher1);

        System.out.println("\nКУРСИ");
        System.out.println(course1);
        System.out.println(course2);
        System.out.println(course3);

        // створюємо студентів
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("Софія");
        student1.setLastName("Дімітрова");
        student1.setEmail("dimitrova@student.ua");
        student1.setCourseYear(2);

        Student student2 = new Student(2L, "Марія", "Шевченко", "shevchenko@student.ua", 3);

        System.out.println("\nСТУДЕНТИ");
        System.out.println(student1);
        System.out.println(student2);

        // реєструємо студентів на курси
        LocalDate today = LocalDate.now();// поточна дата

        // софія записується на Java Programming
        Registration reg1 = new Registration();
        reg1.setId(1L);
        reg1.setRegistrationDate(today);
        reg1.setStudent(student1);
        reg1.setCourse(course1);

        Registration reg2 = new Registration(2L, today, student1, course3);

        Registration reg3 = new Registration(3L, today, student2, course2);

        System.out.println("\nРЕЄСТРАЦІЇ");
        System.out.println(reg1);
        System.out.println(reg2);
        System.out.println(reg3);


        // після завершення курсу - архів з оцінками
        Archive archive1 = new Archive();
        archive1.setId(1L);
        archive1.setGrade(95);
        archive1.setCompletionDate(today);
        archive1.setRegistration(reg1);

        Archive archive2 = new Archive(2L, 80, today, reg2);

        Archive archive3 = new Archive(3L, 88, today, reg3);

        System.out.println("\nАРХІВ ОЦІНОК");
        System.out.println(archive1);
        System.out.println(archive2);
        System.out.println(archive3);


        // додаткова демонстрація доступу
        System.out.println("\nПІДСУМОК");
        System.out.println("Студент: " + archive1.getRegistration().getStudent().getFirstName()
                + " " + archive1.getRegistration().getStudent().getLastName());
        System.out.println("Курс: " + archive1.getRegistration().getCourse().getName());
        System.out.println("Викладач: " +
                archive1.getRegistration().getCourse().getTeacher().getFirstName()
                + " " +
                archive1.getRegistration().getCourse().getTeacher().getLastName());
        System.out.println("Оцінка: " + archive1.getGrade());
    }
}