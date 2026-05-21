package com.example.elective.pattern.observer;

import com.example.elective.entity.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** Логує події запису/відрахування студентів. */
@Component
public class LoggingEnrollmentObserver implements EnrollmentObserver {

    private static final Logger logger = LoggerFactory.getLogger(LoggingEnrollmentObserver.class);

    @Override
    public void onEnroll(Registration registration) {
        logger.info("Студент '{}' записався на курс '{}'",
            registration.getStudent().getFullName(),
            registration.getCourse().getName());
    }

    @Override
    public void onUnenroll(Registration registration) {
        logger.info("Студент '{}' відрахований з курсу '{}'",
            registration.getStudent().getFullName(),
            registration.getCourse().getName());
    }
}