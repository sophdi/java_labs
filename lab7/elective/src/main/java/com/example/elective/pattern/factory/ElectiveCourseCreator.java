package com.example.elective.pattern.factory;

import com.example.elective.entity.Course;
import com.example.elective.entity.Teacher;

/** Фабрика стандартних факультативних курсів (16 тижнів). */
public class ElectiveCourseCreator extends CourseCreator {

    private final int durationWeeks;

    public ElectiveCourseCreator(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    @Override
    public Course createCourse(String name, String description, Teacher teacher) {
        return new Course(name, description, durationWeeks, teacher);
    }
}