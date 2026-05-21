package com.example.elective.pattern.strategy;

import org.springframework.stereotype.Component;

/** Зараховано/не зараховано стратегія. */
@Component("passFailGradeStrategy")
public class PassFailGradeStrategy implements GradeStrategy {

    @Override
    public String evaluate(int grade) {
        return isPassing(grade) ? "Зараховано" : "Не зараховано";
    }

    @Override
    public boolean isPassing(int grade) {
        return grade >= 60;
    }
}