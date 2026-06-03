package com.example.elective.controller;

import com.example.elective.entity.Course;
import com.example.elective.service.CourseService;
import com.example.elective.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контролер для управління курсами.
 * Надає API для CRUD-операцій над сутністю Course.
 */
@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseRestController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseRestController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    /**
     * Повертає список всіх курсів.
     * GET /api/courses
     */
    @GetMapping
    public List<Course> getAll() {
        return courseService.findAll();
    }

    /**
     * Повертає курс за ідентифікатором.
     * GET /api/courses/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Створює новий курс.
     * POST /api/courses
     * Якщо передано teacherId у параметрі запиту — прив'язує викладача.
     */
    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course,
                                         @RequestParam(required = false) Long teacherId) {
        if (teacherId != null) {
            teacherService.findById(teacherId).ifPresent(course::setTeacher);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    /**
     * Оновлює дані курсу.
     * PUT /api/courses/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id,
                                         @Valid @RequestBody Course course,
                                         @RequestParam(required = false) Long teacherId) {
        if (courseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        course.setId(id);
        if (teacherId != null) {
            teacherService.findById(teacherId).ifPresent(course::setTeacher);
        }
        return ResponseEntity.ok(courseService.save(course));
    }

    /**
     * Видаляє курс за ідентифікатором.
     * DELETE /api/courses/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (courseService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
