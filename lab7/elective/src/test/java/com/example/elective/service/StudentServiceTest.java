package com.example.elective.service;

import com.example.elective.entity.Student;
import com.example.elective.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** Тест бізнес-логіки роботи зі студентами. */
@DataJpaTest
@Import(StudentService.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldCreateStudentViaBuilder() {
        Student s = studentService.create("Марія", "Іванова", "ivanova@test.ua", 2);
        assertNotNull(s.getId());
        assertEquals("Марія", s.getFirstName());
        assertEquals("ivanova@test.ua", s.getEmail());
    }

    @Test
    void shouldFindAllStudents() {
        studentService.create("Олег", "Сидоренко", "syd@test.ua", 1);
        studentService.create("Аня", "Петрова", "pet@test.ua", 3);
        List<Student> all = studentService.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void shouldDetectDuplicateEmail() {
        studentService.create("Іван", "Коваль", "koval@test.ua", 4);
        assertTrue(studentService.emailExists("koval@test.ua"));
        assertFalse(studentService.emailExists("other@test.ua"));
    }

    @Test
    void shouldDeleteStudent() {
        Student s = studentService.create("Тест", "Видалення", "del@test.ua", 1);
        Long id = s.getId();
        studentService.delete(id);
        Optional<Student> found = studentService.findById(id);
        assertTrue(found.isEmpty());
    }

    @Test
    void shouldReturnFullName() {
        Student s = studentService.create("Дмитро", "Лисенко", "lys@test.ua", 3);
        assertEquals("Дмитро Лисенко", s.getFullName());
    }
}
