package com.example.elective.repository;

import com.example.elective.entity.Course;
import com.example.elective.entity.Registration;
import com.example.elective.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByStudent(Student student);

    List<Registration> findByCourse(Course course);

    Optional<Registration> findByStudentAndCourse(Student student, Course course);

    boolean existsByStudentAndCourse(Student student, Course course);

    @Query("SELECT r FROM Registration r WHERE r.student.id = :studentId")
    List<Registration> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT r FROM Registration r WHERE r.course.id = :courseId")
    List<Registration> findByCourseId(@Param("courseId") Long courseId);

    /**
     * Повертає реєстрації, для яких ще не виставлено оцінку в архіві.
     * Використовується на сторінці виставлення оцінок, щоб показувати
     * тільки незавершені курси.
     */
    @Query("SELECT r FROM Registration r WHERE r NOT IN (SELECT a.registration FROM Archive a)")
    List<Registration> findUngraded();
}