package com.example.elective.pattern.factory;

import com.example.elective.entity.Course;
import com.example.elective.entity.Teacher;

/** Фабрика коротких курсів (4 тижні). */
public class ShortCourseCreator extends CourseCreator {

    @Override
    public Course createCourse(String name, String description, Teacher teacher) {
        return new Course(name, description, 4, teacher);
    }
}