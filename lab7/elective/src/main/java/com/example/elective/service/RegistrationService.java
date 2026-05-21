package com.example.elective.service;

import com.example.elective.entity.Course;
import com.example.elective.entity.Registration;
import com.example.elective.entity.Student;
import com.example.elective.pattern.command.EnrollCommand;
import com.example.elective.pattern.command.EnrollmentCommand;
import com.example.elective.pattern.command.UnenrollCommand;
import com.example.elective.pattern.observer.EnrollmentObserver;
import com.example.elective.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Бізнес-логіка запису студентів на курси.
 * Використовує патерни Command та Observer.
 */
@Service
@Transactional
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final List<EnrollmentObserver> observers;

    public RegistrationService(RegistrationRepository registrationRepository,
                               List<EnrollmentObserver> observers) {
        this.registrationRepository = registrationRepository;
        this.observers = observers;
    }

    /** Записує студента на курс (Command + Observer). */
    public Registration enroll(Student student, Course course) {
        if (registrationRepository.existsByStudentAndCourse(student, course)) {
            throw new IllegalStateException("Студент вже записаний на цей курс");
        }
        Registration registration = new Registration(LocalDate.now(), student, course);
        EnrollmentCommand command = new EnrollCommand(registration, registrationRepository);
        command.execute();
        observers.forEach(o -> o.onEnroll(registration));
        return registration;
    }

    /** Відраховує студента з курсу (Command + Observer). */
    public void unenroll(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Реєстрацію не знайдено"));
        EnrollmentCommand command = new UnenrollCommand(registration, registrationRepository);
        command.execute();
        observers.forEach(o -> o.onUnenroll(registration));
    }

    @Transactional(readOnly = true)
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Registration> findById(Long id) {
        return registrationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Registration> findByStudent(Student student) {
        return registrationRepository.findByStudent(student);
    }

    @Transactional(readOnly = true)
    public List<Registration> findByCourse(Course course) {
        return registrationRepository.findByCourse(course);
    }

    @Transactional(readOnly = true)
    public List<Registration> findUngraded() {
        return registrationRepository.findUngraded();
    }

    @Transactional(readOnly = true)
    public boolean isEnrolled(Student student, Course course) {
        return registrationRepository.existsByStudentAndCourse(student, course);
    }
}