package com.example.elective.pattern.strategy;

import org.springframework.stereotype.Component;

/** Числова стратегія: відображає оцінку як X/100. */
@Component("numericGradeStrategy")
public class NumericGradeStrategy implements GradeStrategy {

    @Override
    public String evaluate(int grade) {
        return grade + "/100";
    }

    @Override
    public boolean isPassing(int grade) {
        return grade >= 60;
    }
}