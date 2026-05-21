package com.example.elective.service;

import com.example.elective.entity.Student;
import com.example.elective.pattern.builder.StudentBuilder;
import com.example.elective.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Бізнес-логіка для роботи зі студентами.
 * Використовує патерн Builder для створення студентів.
 */
@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /** Створює студента через Builder патерн. */
    public Student create(String firstName, String lastName, String email, int courseYear) {
        Student student = StudentBuilder.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .courseYear(courseYear)
                .build();
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean emailExistsForOther(String email, Long id) {
        return studentRepository.existsByEmailAndIdNot(email, id);
    }
}