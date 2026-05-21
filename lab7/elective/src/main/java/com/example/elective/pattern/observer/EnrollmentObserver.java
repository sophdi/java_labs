package com.example.elective.pattern.observer;

import com.example.elective.entity.Registration;

/**
 * Спостерігач за записом на курс (GoF Observer).
 * Сповіщається при зміні стану реєстрації.
 */
public interface EnrollmentObserver {
    void onEnroll(Registration registration);
    void onUnenroll(Registration registration);
}