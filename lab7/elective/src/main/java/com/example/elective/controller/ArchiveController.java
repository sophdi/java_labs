package com.example.elective.controller;

import com.example.elective.entity.Archive;
import com.example.elective.entity.Registration;
import com.example.elective.service.ArchiveService;
import com.example.elective.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контролер для управління архівом оцінок.
 * Дозволяє переглядати результати курсів та виставляти оцінки студентам.
 * Виставлення оцінки використовує патерн Strategy через {@link ArchiveService}.
 */
@Controller
@RequestMapping("/archive")
public class ArchiveController {

    private final ArchiveService archiveService;
    private final RegistrationService registrationService;

    public ArchiveController(ArchiveService archiveService, RegistrationService registrationService) {
        this.archiveService = archiveService;
        this.registrationService = registrationService;
    }

    /**
     * Відображає всі записи архіву з оцінками студентів.
     *
     * @param model модель для передачі даних у шаблон
     * @return шаблон archive/list
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("archives", archiveService.findAll());
        return "archive/list";
    }

    /**
     * Відображає форму виставлення оцінки.
     * Показує лише реєстрації, що ще не мають оцінки в архіві.
     *
     * @param model модель зі списком реєстрацій без оцінок
     * @return шаблон archive/grade-form
     */
    @GetMapping("/grade")
    public String gradeForm(Model model) {
        model.addAttribute("ungraded", registrationService.findUngraded());
        return "archive/grade-form";
    }

    /**
     * Обробляє виставлення оцінки студенту.
     * Оцінка зберігається в архіві та відображається у текстовому вигляді
     * відповідно до поточної стратегії оцінювання (за замовчуванням — п'ятибальна).
     *
     * @param registrationId     ідентифікатор реєстрації (студент + курс)
     * @param gradeValue         числова оцінка від 0 до 100
     * @param redirectAttributes атрибути для flash-повідомлення з результатом
     * @return редирект на архів
     */
    @PostMapping("/grade")
    public String grade(@RequestParam Long registrationId, @RequestParam int gradeValue,
                        RedirectAttributes redirectAttributes) {
        Registration registration = registrationService.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Реєстрацію не знайдено"));
        try {
            archiveService.grade(registration, gradeValue);
            // Текстовий результат формується через Strategy патерн
            String evaluated = archiveService.evaluateGrade(gradeValue);
            redirectAttributes.addFlashAttribute("success", "Оцінка виставлена: " + evaluated);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/archive";
    }

    /**
     * Видаляє запис з архіву оцінок.
     *
     * @param id                 ідентифікатор запису архіву
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на архів
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        archiveService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Запис з архіву видалено");
        return "redirect:/archive";
    }
}
