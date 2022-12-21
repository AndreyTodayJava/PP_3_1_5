package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("admin", userService.findAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user, @RequestParam("rolesName") Set<String> listOfRoles) {
        userService.save(user,listOfRoles);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute("user") User user, @RequestParam(value = "rolesName",
            defaultValue = "ROLE_USER") Set<String> listOfRoles) {
        userService.update(id, user, listOfRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
