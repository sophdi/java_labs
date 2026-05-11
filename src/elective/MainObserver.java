package elective;

public class MainObserver {

    public static void main(String[] args) {

        ElectiveFacade elective = new ElectiveFacade();

        Teacher teacher1 = new Teacher(1L, "Олена", "Коваленко", "kovalenko@uni.ua");
        Teacher teacher2 = new Teacher(2L, "Микола", "Бондаренко", "bondarenko@uni.ua");

        Course course1 = new Course(1L, "Java Programming", "Основи Java", 12, teacher1);
        Course course2 = new Course(2L, "Web Design", "HTML та CSS", 8, teacher2);

        Student student1 = new Student(1L, "Софія", "Дімітрова", "dimitrova@student.ua", 2);
        Student student2 = new Student(2L, "Марія", "Шевченко", "shevchenko@student.ua", 3);

        elective.addCourse(course1);
        elective.addCourse(course2);
        elective.addStudent(student1);
        elective.addStudent(student2);

        // підписуємо викладачів як спостерігачів
        elective.addObserver(new TeacherObserver(teacher1));
        elective.addObserver(new TeacherObserver(teacher2));

        System.out.println("=== РЕЄСТРАЦІЯ НА КУРСИ ===");
        elective.registerStudentToCourse(1L, 1L); // Дімітрова → Java (Коваленко отримає сповіщення)
        elective.registerStudentToCourse(1L, 2L); // Дімітрова → Web Design (Бондаренко отримає сповіщення)
        elective.registerStudentToCourse(2L, 1L); // Шевченко → Java (Коваленко отримає сповіщення)
    }
}