package com.example.elective.pattern.strategy;

import org.springframework.stereotype.Component;

/** П'ятибальна стратегія: відображає оцінку за шкалою 2-5. */
@Component("fivePointGradeStrategy")
public class FivePointGradeStrategy implements GradeStrategy {

    @Override
    public String evaluate(int grade) {
        if (grade >= 90) return "Відмінно (5)";
        if (grade >= 75) return "Добре (4)";
        if (grade >= 60) return "Задовільно (3)";
        return "Незадовільно (2)";
    }

    @Override
    public boolean isPassing(int grade) {
        return grade >= 60;
    }
}