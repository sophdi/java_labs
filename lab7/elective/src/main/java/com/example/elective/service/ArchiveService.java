package com.example.elective.service;

import com.example.elective.entity.Archive;
import com.example.elective.entity.Registration;
import com.example.elective.pattern.strategy.FivePointGradeStrategy;
import com.example.elective.pattern.strategy.GradeStrategy;
import com.example.elective.repository.ArchiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Бізнес-логіка архіву оцінок.
 * Використовує патерн Strategy для оцінювання.
 */
@Service
@Transactional
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private GradeStrategy gradeStrategy;

    public ArchiveService(ArchiveRepository archiveRepository,
                          FivePointGradeStrategy fivePointGradeStrategy) {
        this.archiveRepository = archiveRepository;
        // стратегія за замовчуванням
        this.gradeStrategy = fivePointGradeStrategy;
    }

    /** Встановлює стратегію оцінювання (Strategy патерн). */
    public void setGradeStrategy(GradeStrategy strategy) {
        this.gradeStrategy = strategy;
    }

    /** Виставляє оцінку студенту за реєстрацію. */
    public Archive grade(Registration registration, int gradeValue) {
        if (archiveRepository.existsByRegistration(registration)) {
            throw new IllegalStateException("Оцінка вже виставлена для цієї реєстрації");
        }
        Archive archive = new Archive(gradeValue, LocalDate.now(), registration);
        return archiveRepository.save(archive);
    }

    /** Повертає текстову оцінку згідно поточної стратегії. */
    public String evaluateGrade(int grade) {
        return gradeStrategy.evaluate(grade);
    }

    /** Перевіряє, чи є оцінка прохідною. */
    public boolean isPassing(int grade) {
        return gradeStrategy.isPassing(grade);
    }

    @Transactional(readOnly = true)
    public List<Archive> findAll() {
        return archiveRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Archive> findById(Long id) {
        return archiveRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Archive> findByStudentId(Long studentId) {
        return archiveRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Archive> findByCourseId(Long courseId) {
        return archiveRepository.findByCourseId(courseId);
    }

    @Transactional(readOnly = true)
    public boolean hasGrade(Registration registration) {
        return archiveRepository.existsByRegistration(registration);
    }

    public void delete(Long id) {
        archiveRepository.deleteById(id);
    }
}