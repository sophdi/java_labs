package com.example.elective.controller;

import com.example.elective.entity.Teacher;
import com.example.elective.service.CourseService;
import com.example.elective.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контролер для управління викладачами.
 * Реалізує CRUD-операції: список, створення, редагування та видалення.
 */
@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    /**
     * Відображає список усіх викладачів із кількістю закріплених курсів.
     *
     * @param model модель для передачі даних у шаблон
     * @return шаблон teacher/list
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teacher/list";
    }

    /**
     * Відображає форму для створення нового викладача.
     *
     * @param model модель із порожнім об'єктом Teacher
     * @return шаблон teacher/form
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/form";
    }

    /**
     * Обробляє форму створення викладача з перевіркою унікальності email.
     *
     * @param teacher            об'єкт викладача з даними форми
     * @param result             результат валідації Bean Validation
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список або форма при помилці
     */
    @PostMapping
    public String create(@Valid @ModelAttribute Teacher teacher, BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "teacher/form";
        if (teacherService.emailExists(teacher.getEmail())) {
            result.rejectValue("email", "duplicate", "Цей email вже використовується");
            return "teacher/form";
        }
        teacherService.save(teacher);
        redirectAttributes.addFlashAttribute("success", "Викладача успішно додано");
        return "redirect:/teachers";
    }

    /**
     * Відображає форму редагування викладача.
     *
     * @param id    ідентифікатор викладача
     * @param model модель із поточними даними викладача
     * @return шаблон teacher/form
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Викладача не знайдено: " + id));
        model.addAttribute("teacher", teacher);
        return "teacher/form";
    }

    /**
     * Обробляє форму оновлення даних викладача.
     *
     * @param id                 ідентифікатор викладача
     * @param teacher            об'єкт із новими даними
     * @param result             результат валідації
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список або форма при помилці
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Teacher teacher,
                         BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "teacher/form";
        if (teacherService.emailExistsForOther(teacher.getEmail(), id)) {
            result.rejectValue("email", "duplicate", "Цей email вже використовується");
            return "teacher/form";
        }
        teacher.setId(id);
        teacherService.save(teacher);
        redirectAttributes.addFlashAttribute("success", "Дані викладача оновлено");
        return "redirect:/teachers";
    }

    /**
     * Видаляє викладача за ідентифікатором.
     *
     * @param id                 ідентифікатор викладача
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список викладачів
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        teacherService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Викладача видалено");
        return "redirect:/teachers";
    }
}