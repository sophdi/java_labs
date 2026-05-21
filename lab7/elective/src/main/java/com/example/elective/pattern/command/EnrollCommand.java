package com.example.elective.pattern.command;

import com.example.elective.entity.Registration;
import com.example.elective.repository.RegistrationRepository;

/** Команда запису студента на курс. */
public class EnrollCommand implements EnrollmentCommand {

    private final Registration registration;
    private final RegistrationRepository repository;

    public EnrollCommand(Registration registration, RegistrationRepository repository) {
        this.registration = registration;
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.save(registration);
    }

    @Override
    public void undo() {
        if (registration.getId() != null) {
            repository.delete(registration);
        }
    }
}