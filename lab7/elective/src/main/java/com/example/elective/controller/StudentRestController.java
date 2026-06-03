package com.example.elective.controller;

import com.example.elective.entity.Student;
import com.example.elective.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контролер для управління студентами.
 * Надає API для CRUD-операцій над сутністю Student.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Повертає список всіх студентів.
     * GET /api/students
     */
    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    /**
     * Повертає студента за ідентифікатором.
     * GET /api/students/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Створює нового студента.
     * POST /api/students
     */
    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        if (studentService.emailExists(student.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Student saved = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Оновлює дані студента.
     * PUT /api/students/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,
                                          @Valid @RequestBody Student student) {
        if (studentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (studentService.emailExistsForOther(student.getEmail(), id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        student.setId(id);
        return ResponseEntity.ok(studentService.save(student));
    }

    /**
     * Видаляє студента за ідентифікатором.
     * DELETE /api/students/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
