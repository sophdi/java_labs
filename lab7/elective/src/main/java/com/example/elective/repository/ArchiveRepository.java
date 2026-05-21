package com.example.elective.repository;

import com.example.elective.entity.Archive;
import com.example.elective.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    /** Знаходить запис архіву за реєстрацією (один студент — один курс = одна оцінка). */
    Optional<Archive> findByRegistration(Registration registration);

    /** Перевіряє, чи вже виставлено оцінку для даної реєстрації. */
    boolean existsByRegistration(Registration registration);

    /** Повертає всі оцінки студента, відсортовані від новіших до старіших. */
    @Query("SELECT a FROM Archive a WHERE a.registration.student.id = :studentId ORDER BY a.completionDate DESC")
    List<Archive> findByStudentId(@Param("studentId") Long studentId);

    /** Повертає всі оцінки по курсу, відсортовані від вищих до нижчих. */
    @Query("SELECT a FROM Archive a WHERE a.registration.course.id = :courseId ORDER BY a.grade DESC")
    List<Archive> findByCourseId(@Param("courseId") Long courseId);

    /** Обчислює середню оцінку по курсу для відображення статистики. */
    @Query("SELECT AVG(a.grade) FROM Archive a WHERE a.registration.course.id = :courseId")
    Double averageGradeByCourseId(@Param("courseId") Long courseId);
}