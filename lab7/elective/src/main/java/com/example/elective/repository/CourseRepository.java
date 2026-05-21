package com.example.elective.repository;

import com.example.elective.entity.Course;
import com.example.elective.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTeacher(Teacher teacher);

    List<Course> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId")
    List<Course> findByTeacherId(@Param("teacherId") Long teacherId);
}