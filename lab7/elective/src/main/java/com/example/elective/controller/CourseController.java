package com.example.elective.controller;

import com.example.elective.entity.Course;
import com.example.elective.service.ArchiveService;
import com.example.elective.service.CourseService;
import com.example.elective.service.RegistrationService;
import com.example.elective.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контролер для управління факультативними курсами.
 * Забезпечує CRUD-операції та перегляд детальної інформації з реєстраціями й оцінками.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final RegistrationService registrationService;
    private final ArchiveService archiveService;

    public CourseController(CourseService courseService, TeacherService teacherService,
                            RegistrationService registrationService, ArchiveService archiveService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.registrationService = registrationService;
        this.archiveService = archiveService;
    }

    /**
     * Відображає каталог усіх курсів.
     *
     * @param model модель для передачі даних у шаблон
     * @return шаблон course/list
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/list";
    }

    /**
     * Відображає деталі курсу: опис, список записаних студентів та їхні оцінки.
     *
     * @param id    ідентифікатор курсу
     * @param model модель для передачі даних у шаблон
     * @return шаблон course/view
     */
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Курс не знайдено: " + id));
        model.addAttribute("course", course);
        model.addAttribute("registrations", registrationService.findByCourse(course));
        model.addAttribute("grades", archiveService.findByCourseId(id));
        return "course/view";
    }

    /**
     * Відображає форму для створення нового курсу.
     * Передає список викладачів для вибору у формі.
     *
     * @param model модель із порожнім курсом та списком викладачів
     * @return шаблон course/form
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("teachers", teacherService.findAll());
        return "course/form";
    }

    /**
     * Обробляє форму створення курсу.
     * teacherId передається окремим параметром, оскільки вкладений об'єкт Teacher
     * не може бути прив'язаний безпосередньо через @ModelAttribute без конвертера.
     *
     * @param course             об'єкт курсу з даними форми
     * @param result             результат валідації
     * @param teacherId          ідентифікатор обраного викладача (може бути null)
     * @param model              модель для повторного відображення форми при помилці
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список або форма при помилці
     */
    @PostMapping
    public String create(@Valid @ModelAttribute Course course, BindingResult result,
                         @RequestParam(required = false) Long teacherId,
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("teachers", teacherService.findAll());
            return "course/form";
        }
        if (teacherId != null) {
            teacherService.findById(teacherId).ifPresent(course::setTeacher);
        }
        courseService.save(course);
        redirectAttributes.addFlashAttribute("success", "Курс успішно додано");
        return "redirect:/courses";
    }

    /**
     * Відображає форму редагування курсу.
     *
     * @param id    ідентифікатор курсу
     * @param model модель із поточними даними курсу та списком викладачів
     * @return шаблон course/form
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Курс не знайдено: " + id));
        model.addAttribute("course", course);
        model.addAttribute("teachers", teacherService.findAll());
        return "course/form";
    }

    /**
     * Обробляє форму оновлення курсу.
     *
     * @param id                 ідентифікатор курсу, що оновлюється
     * @param course             об'єкт із новими даними
     * @param result             результат валідації
     * @param teacherId          ідентифікатор нового викладача (може бути null)
     * @param model              модель для повторного відображення форми при помилці
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на сторінку курсу або форма при помилці
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Course course,
                         BindingResult result, @RequestParam(required = false) Long teacherId,
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("teachers", teacherService.findAll());
            return "course/form";
        }
        course.setId(id);
        if (teacherId != null) {
            teacherService.findById(teacherId).ifPresent(course::setTeacher);
        }
        courseService.save(course);
        redirectAttributes.addFlashAttribute("success", "Дані курсу оновлено");
        return "redirect:/courses/" + id;
    }

    /**
     * Видаляє курс за ідентифікатором.
     *
     * @param id                 ідентифікатор курсу
     * @param redirectAttributes атрибути для flash-повідомлення
     * @return редирект на список курсів
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Курс видалено");
        return "redirect:/courses";
    }
}
