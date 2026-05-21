package com.example.elective.repository;

import com.example.elective.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /** Знаходить студента за email (використовується для перевірки унікальності). */
    Optional<Student> findByEmail(String email);

    /** Пошук студентів за частиною прізвища без урахування регістру. */
    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    /** Повертає всіх студентів вказаного курсу навчання, відсортованих за прізвищем. */
    @Query("SELECT s FROM Student s WHERE s.courseYear = :year ORDER BY s.lastName")
    List<Student> findByCourseYear(@Param("year") int year);

    /** Перевіряє наявність студента з таким email (для валідації при створенні). */
    boolean existsByEmail(String email);

    /** Перевіряє наявність іншого студента з таким email (для валідації при редагуванні). */
    boolean existsByEmailAndIdNot(String email, Long id);
}