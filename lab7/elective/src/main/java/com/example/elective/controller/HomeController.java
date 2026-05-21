package com.example.elective.controller;

import com.example.elective.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контролер головної сторінки.
 * Формує зведену статистику по всіх сутностях системи.
 */
@Controller
public class HomeController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final RegistrationService registrationService;
    private final ArchiveService archiveService;

    public HomeController(StudentService studentService, TeacherService teacherService,
                          CourseService courseService, RegistrationService registrationService,
                          ArchiveService archiveService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.registrationService = registrationService;
        this.archiveService = archiveService;
    }

    /**
     * Відображає головну сторінку з лічильниками сутностей.
     *
     * @param model модель Spring MVC для передачі даних у шаблон
     * @return назва FreeMarker-шаблону
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("studentCount", studentService.findAll().size());
        model.addAttribute("teacherCount", teacherService.findAll().size());
        model.addAttribute("courseCount", courseService.findAll().size());
        model.addAttribute("registrationCount", registrationService.findAll().size());
        model.addAttribute("archiveCount", archiveService.findAll().size());
        return "index";
    }
}
