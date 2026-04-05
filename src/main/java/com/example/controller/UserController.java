package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Model model) {
        // Получаем имя текущего пользователя из контекста безопасности
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        // Ищем пользователя по username
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "user";
    }
}