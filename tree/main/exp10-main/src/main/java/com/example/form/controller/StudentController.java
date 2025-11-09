package com.example.form.controller;

import com.example.form.model.Student;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final List<Student> studentStore = new ArrayList<>();

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String submitForm(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "students/form";
        }
        studentStore.add(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student registered successfully!");
        return "redirect:/students";
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentStore);
        return "students/list";
    }
}
