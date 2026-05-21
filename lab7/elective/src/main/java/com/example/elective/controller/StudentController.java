package com.example.elective.controller;

import com.example.elective.entity.Student;
import com.example.elective.service.ArchiveService;
import com.example.elective.service.RegistrationService;
import com.example.elective.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контролер для управління студентами.
 * Реалізує CRUD-операції: перегляд списку, деталей, створення, редагування та видалення.
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final RegistrationService registrationService;
    private final ArchiveService archiveService;

    public StudentController(StudentService studentService,
                             RegistrationService registrationService,
                             ArchiveService archiveService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.archiveService = archiveService;
    }

    /**
     * Відображає список всіх студентів.
     *
     * @param model модель для передачі даних у шаблон
     * @return шаблон student/list
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/list";
    }

    /**
     * Відображає детальну інформацію про студента разом із його реєстраціями та оцінками.
     *
     * @param id    ідентифікатор студента
     * @param model модель для передачі даних у шаблон
     * @return шаблон student/view
     */
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Студента не знайдено: " + id));
        model.addAttribute("student", student);
        model.addAttribute("registrations", registrationService.findByStudent(student));
        model.addAttribute("grades", archiveService.findByStudentId(id));
        return "student/view";
    }

    /**
     * Відображає форму для створення нового студента.
     *
     * @param model модель із порожнім об'єктом Student для прив'язки форми
     * @return шаблон student/form
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    /**
     * Обробляє форму створення студента.
     * Перевіряє валідацію полів та унікальність email перед збереженням.
     *
     * @param student            об'єкт студента, заповнений з форми
     * @param result             результат валідації Bean Validation
     * @param redirectAttributes атрибути для flash-повідомлення після редиректу
     * @return редирект на список або повернення до форми при помилці
     */
    @PostMapping
    public String create(@Valid @ModelAttribute Student student, BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "student/form";
        }
        // Перевірка унікальності email (обмеження БД не дає зрозумілого повідомлення)
        if (studentService.emailExists(student.getEmail())) {
            result.rejectValue("email", "duplicate", "Цей email вже використовується");
            return "student/form";
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("success", "Студента успішно додано");
        return "redirect:/students";
    }

    /**
     * Відображає форму редагування існуючого студента.
     *
     * @param id    ідентифікатор студента
     * @param model модель із даними студента для заповнення форми
     * @return шаблон student/form
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Студента не знайдено: " + id));
        model.addAttribute("student", student);
        return "student/form";
    }

    /**
     * Обробляє форму оновлення даних студента.
     * При перевірці email виключає поточного студента, щоб не блокувати збереження без змін.
     *
     * @param id                 ідентифікатор студента, що оновлюється
     * @param student            об'єкт із новими даними
     * @param result             результат валідації
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на сторінку студента або повернення до форми при помилці
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Student student,
                         BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "student/form";
        }
        if (studentService.emailExistsForOther(student.getEmail(), id)) {
            result.rejectValue("email", "duplicate", "Цей email вже використовується");
            return "student/form";
        }
        student.setId(id);
        studentService.save(student);
        redirectAttributes.addFlashAttribute("success", "Дані студента оновлено");
        return "redirect:/students/" + id;
    }

    /**
     * Видаляє студента за ідентифікатором.
     *
     * @param id                 ідентифікатор студента
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список студентів
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Студента видалено");
        return "redirect:/students";
    }
}
