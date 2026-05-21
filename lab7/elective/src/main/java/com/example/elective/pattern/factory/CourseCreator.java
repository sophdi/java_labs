package com.example.elective.pattern.factory;

import com.example.elective.entity.Course;
import com.example.elective.entity.Teacher;

/**
 * Абстрактний фабричний метод для створення курсів (GoF Factory Method).
 * Підкласи визначають конкретний тип курсу.
 */
public abstract class CourseCreator {

    /** Фабричний метод — реалізується підкласами. */
    public abstract Course createCourse(String name, String description, Teacher teacher);

    /** Шаблонний метод: створює та ініціалізує курс. */
    public final Course initializeCourse(String name, String description, Teacher teacher) {
        Course course = createCourse(name, description, teacher);
        // спільна ініціалізація для всіх типів курсів
        return course;
    }
}