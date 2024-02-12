package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "add";
    }

    @PostMapping
    public String add(@ModelAttribute("user") User user,
                      Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        userService.delete(id);
        return "redirect:/admin";
    }
}
