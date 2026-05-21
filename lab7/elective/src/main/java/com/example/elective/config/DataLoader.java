package com.example.elective.config;

import com.example.elective.entity.*;
import com.example.elective.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Завантажує тестові дані при старті (тільки якщо БД порожня).
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final RegistrationService registrationService;
    private final ArchiveService archiveService;

    public DataLoader(StudentService studentService, TeacherService teacherService,
                      CourseService courseService, RegistrationService registrationService,
                      ArchiveService archiveService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.registrationService = registrationService;
        this.archiveService = archiveService;
    }

    @Override
    public void run(String... args) {
        if (!teacherService.findAll().isEmpty()) return;

        // Викладачі
        Teacher t1 = teacherService.save(new Teacher("Іван", "Петренко", "petrenko@univ.ua"));
        Teacher t2 = teacherService.save(new Teacher("Олена", "Коваль", "koval@univ.ua"));

        // Курси (через Factory Method у CourseService)
        Course c1 = courseService.createElective("Алгоритми та структури даних",
                "Основні алгоритми сортування, пошуку та структури даних", 16, t1);
        Course c2 = courseService.createElective("Веб-розробка на Java",
                "Spring Boot, REST API, бази даних", 16, t1);
        Course c3 = courseService.createShort("Основи машинного навчання",
                "Введення в ML, Python, sklearn", t2);
        Course c4 = courseService.createElective("Бази даних",
                "SQL, PostgreSQL, нормалізація", 12, t2);

        // Студенти (через Builder у StudentService)
        Student s1 = studentService.create("Марія", "Шевченко", "shevchenko@student.ua", 2);
        Student s2 = studentService.create("Олексій", "Бондаренко", "bondarenko@student.ua", 3);
        Student s3 = studentService.create("Аліна", "Мороз", "moroz@student.ua", 2);
        Student s4 = studentService.create("Дмитро", "Лисенко", "lysenko@student.ua", 4);

        // Реєстрації (Command + Observer)
        Registration r1 = registrationService.enroll(s1, c1);
        Registration r2 = registrationService.enroll(s1, c2);
        Registration r3 = registrationService.enroll(s2, c1);
        Registration r4 = registrationService.enroll(s2, c3);
        Registration r5 = registrationService.enroll(s3, c4);
        Registration r6 = registrationService.enroll(s4, c2);

        // Архів (оцінки) — Strategy за замовчуванням: п'ятибальна
        archiveService.grade(r1, 88);
        archiveService.grade(r3, 72);
        archiveService.grade(r5, 95);
    }
}