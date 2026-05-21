package com.example.elective.pattern.strategy;

/**
 * Стратегія оцінювання (GoF Strategy).
 * Дозволяє замінювати алгоритм підрахунку оцінки без зміни клієнтського коду.
 */
public interface GradeStrategy {
    /** Повертає текстове представлення оцінки. */
    String evaluate(int grade);
    /** Перевіряє, чи є оцінка прохідною. */
    boolean isPassing(int grade);
}