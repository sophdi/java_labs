package com.example.elective.service;

import com.example.elective.entity.Course;
import com.example.elective.entity.Teacher;
import com.example.elective.pattern.factory.CourseCreator;
import com.example.elective.pattern.factory.ElectiveCourseCreator;
import com.example.elective.pattern.factory.ShortCourseCreator;
import com.example.elective.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Бізнес-логіка для роботи з курсами.
 * Використовує патерн Factory Method для створення різних типів курсів.
 */
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    /** Створює стандартний факультативний курс через Factory Method. */
    public Course createElective(String name, String description, int weeks, Teacher teacher) {
        CourseCreator creator = new ElectiveCourseCreator(weeks);
        return courseRepository.save(creator.initializeCourse(name, description, teacher));
    }

    /** Створює короткий курс (4 тижні) через Factory Method. */
    public Course createShort(String name, String description, Teacher teacher) {
        CourseCreator creator = new ShortCourseCreator();
        return courseRepository.save(creator.initializeCourse(name, description, teacher));
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Course> findByTeacher(Teacher teacher) {
        return courseRepository.findByTeacher(teacher);
    }
}