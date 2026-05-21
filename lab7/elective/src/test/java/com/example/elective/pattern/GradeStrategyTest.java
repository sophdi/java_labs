package com.example.elective.pattern;

import com.example.elective.pattern.strategy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Тест патерну Strategy для оцінювання. */
class GradeStrategyTest {

    @Test
    void numericStrategyShouldFormat() {
        GradeStrategy strategy = new NumericGradeStrategy();
        assertEquals("85/100", strategy.evaluate(85));
        assertTrue(strategy.isPassing(60));
        assertFalse(strategy.isPassing(59));
    }

    @Test
    void fivePointStrategyShouldClassify() {
        GradeStrategy strategy = new FivePointGradeStrategy();
        assertEquals("Відмінно (5)", strategy.evaluate(95));
        assertEquals("Добре (4)", strategy.evaluate(80));
        assertEquals("Задовільно (3)", strategy.evaluate(65));
        assertEquals("Незадовільно (2)", strategy.evaluate(40));
    }

    @Test
    void passFailStrategyShouldDetermineResult() {
        GradeStrategy strategy = new PassFailGradeStrategy();
        assertEquals("Зараховано", strategy.evaluate(60));
        assertEquals("Не зараховано", strategy.evaluate(59));
    }

    @Test
    void builderShouldCreateStudent() {
        com.example.elective.pattern.builder.StudentBuilder builder =
            com.example.elective.pattern.builder.StudentBuilder.builder();
        com.example.elective.entity.Student s = builder
            .firstName("Іван")
            .lastName("Франко")
            .email("franko@ua")
            .courseYear(3)
            .build();
        assertEquals("Іван", s.getFirstName());
        assertEquals("Іван Франко", s.getFullName());
    }
}
