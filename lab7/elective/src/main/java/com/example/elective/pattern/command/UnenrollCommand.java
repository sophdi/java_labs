package com.example.elective.pattern.command;

import com.example.elective.entity.Registration;
import com.example.elective.repository.RegistrationRepository;

/** Команда відрахування студента з курсу. */
public class UnenrollCommand implements EnrollmentCommand {

    private final Registration registration;
    private final RegistrationRepository repository;

    public UnenrollCommand(Registration registration, RegistrationRepository repository) {
        this.registration = registration;
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.delete(registration);
    }

    @Override
    public void undo() {
        repository.save(registration);
    }
}