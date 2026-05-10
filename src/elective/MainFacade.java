package elective;

public class MainFacade {

    public static void main(String[] args) {

        // створюємо фасад — єдину точку входу до системи
        ElectiveFacade elective = new ElectiveFacade();

        Teacher teacher1 = new Teacher(1L, "Олена", "Коваленко", "kovalenko@uni.ua");
        Teacher teacher2 = new Teacher(2L, "Микола", "Бондаренко", "bondarenko@uni.ua");

        Course course1 = new Course(1L, "Java Programming", "Основи Java", 12, teacher1);
        Course course2 = new Course(2L, "Web Design", "HTML та CSS", 8, teacher2);

        Student student1 = new Student(1L, "Софія", "Дімітрова", "dimitrova@student.ua", 2);
        Student student2 = new Student(2L, "Марія", "Шевченко", "shevchenko@student.ua", 3);

        // наповнюємо систему
        elective.addCourse(course1);
        elective.addCourse(course2);
        elective.addStudent(student1);
        elective.addStudent(student2);

        // реєстрація студентів на курси через фасад
        System.out.println("=== РЕЄСТРАЦІЯ НА КУРСИ ===");
        elective.registerStudentToCourse(1L, 1L);
        elective.registerStudentToCourse(1L, 2L);
        elective.registerStudentToCourse(2L, 1L);
        elective.registerStudentToCourse(2L, 1L); // дублювання
        elective.registerStudentToCourse(99L, 1L); // неіснуючий студент

        elective.printAllRegistrations();

        // виставлення оцінок через фасад
        System.out.println("\n=== ВИСТАВЛЕННЯ ОЦІНОК ===");
        elective.gradeStudent(1L, 1L, 95);
        elective.gradeStudent(1L, 1L, 90); // повторна спроба
        elective.gradeStudent(2L, 1L, 78);
        elective.gradeStudent(1L, 2L, 88);
        elective.gradeStudent(1L, 1L, 150); // некоректна оцінка

        elective.printArchive();
    }
}