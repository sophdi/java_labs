package com.example.elective.service;

import com.example.elective.entity.Course;
import com.example.elective.entity.Registration;
import com.example.elective.entity.Student;
import com.example.elective.entity.Teacher;
import com.example.elective.pattern.observer.LoggingEnrollmentObserver;
import com.example.elective.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Тест бізнес-логіки запису студентів на курси (Command + Observer). */
@DataJpaTest
@Import({RegistrationService.class, StudentService.class, TeacherService.class,
         CourseService.class, LoggingEnrollmentObserver.class})
class RegistrationServiceTest {

    @Autowired private RegistrationService registrationService;
    @Autowired private StudentRepository studentRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private CourseRepository courseRepository;

    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        Teacher teacher = teacherRepository.save(new Teacher("Тест", "Викладач", "teacher@test.ua"));
        student = studentRepository.save(new Student("Тест", "Студент", "student@test.ua", 2));
        course = courseRepository.save(new Course("Тестовий курс", "Опис", 8, teacher));
    }

    @Test
    void shouldEnrollStudentInCourse() {
        Registration r = registrationService.enroll(student, course);
        assertNotNull(r.getId());
        assertEquals(student, r.getStudent());
        assertEquals(course, r.getCourse());
        assertNotNull(r.getRegistrationDate());
    }

    @Test
    void shouldPreventDuplicateEnrollment() {
        registrationService.enroll(student, course);
        assertThrows(IllegalStateException.class,
            () -> registrationService.enroll(student, course));
    }

    @Test
    void shouldUnenrollStudent() {
        Registration r = registrationService.enroll(student, course);
        Long id = r.getId();
        registrationService.unenroll(id);
        assertFalse(registrationService.isEnrolled(student, course));
    }

    @Test
    void shouldCheckEnrollmentStatus() {
        assertFalse(registrationService.isEnrolled(student, course));
        registrationService.enroll(student, course);
        assertTrue(registrationService.isEnrolled(student, course));
    }

    @Test
    void shouldFindRegistrationsByStudent() {
        registrationService.enroll(student, course);
        List<Registration> list = registrationService.findByStudent(student);
        assertEquals(1, list.size());
    }
}
