package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    // Страница логина (GET)
    @GetMapping("/login")
    public String login() {
        System.out.println("=== GET /login called ===");
        return "login";  // ищем JSP страницу /WEB-INF/views/login.jsp
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/admin";
    }

    // ВРЕМЕННЫЙ метод для отладки POST-запросов
    @PostMapping("/login")
    @ResponseBody  // ← Отправляем текст, а не ищем JSP
    public String testLogin(@RequestParam String username, @RequestParam String password) {
        System.out.println("=== POST /login called with: " + username + " / " + password);
        return "Login received: " + username + " / " + password;
    }
}