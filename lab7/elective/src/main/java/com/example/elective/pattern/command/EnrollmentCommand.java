package com.example.elective.pattern.command;

/**
 * Команда запису/відрахування (GoF Command).
 * Підтримує виконання та скасування операції.
 */
public interface EnrollmentCommand {
    void execute();
    void undo();
}