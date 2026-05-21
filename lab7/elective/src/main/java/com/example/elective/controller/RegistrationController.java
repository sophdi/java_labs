package com.example.elective.controller;

import com.example.elective.entity.Course;
import com.example.elective.entity.Student;
import com.example.elective.service.CourseService;
import com.example.elective.service.RegistrationService;
import com.example.elective.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контролер для управління записами студентів на курси.
 * Делегує бізнес-логіку (Command + Observer) до {@link RegistrationService}.
 */
@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    public RegistrationController(RegistrationService registrationService,
                                  StudentService studentService,
                                  CourseService courseService) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Відображає список усіх активних реєстрацій.
     *
     * @param model модель для передачі даних у шаблон
     * @return шаблон registration/list
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("registrations", registrationService.findAll());
        return "registration/list";
    }

    /**
     * Відображає форму запису студента на курс.
     * Підтримує попереднє заповнення через параметри studentId та courseId
     * (використовується з кнопок "Записати" на сторінках студента та курсу).
     *
     * @param studentId попередньо обраний студент (необов'язково)
     * @param courseId  попередньо обраний курс (необов'язково)
     * @param model     модель зі списками студентів та курсів
     * @return шаблон registration/form
     */
    @GetMapping("/new")
    public String newForm(@RequestParam(required = false) Long studentId,
                          @RequestParam(required = false) Long courseId,
                          Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("selectedStudentId", studentId);
        model.addAttribute("selectedCourseId", courseId);
        return "registration/form";
    }

    /**
     * Обробляє форму запису студента на курс.
     * Викликає {@link RegistrationService#enroll}, який виконує Command та сповіщає Observer.
     * При повторному записі на той самий курс повертає повідомлення про помилку.
     *
     * @param studentId          ідентифікатор студента
     * @param courseId           ідентифікатор курсу
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список реєстрацій
     */
    @PostMapping
    public String create(@RequestParam Long studentId, @RequestParam Long courseId,
                         RedirectAttributes redirectAttributes) {
        Student student = studentService.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студента не знайдено"));
        Course course = courseService.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Курс не знайдено"));
        try {
            registrationService.enroll(student, course);
            redirectAttributes.addFlashAttribute("success",
                    student.getFullName() + " записаний на курс \"" + course.getName() + "\"");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/registrations";
    }

    /**
     * Скасовує запис студента на курс.
     * Викликає {@link RegistrationService#unenroll}, який виконує UnenrollCommand та Observer.
     *
     * @param id                 ідентифікатор реєстрації
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список реєстрацій
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            registrationService.unenroll(id);
            redirectAttributes.addFlashAttribute("success", "Реєстрацію скасовано");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/registrations";
    }
}
