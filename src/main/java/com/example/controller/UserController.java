package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // (1) Говорим Spring: это контроллер (обработчик веб-запросов)
@RequestMapping("/users")  // (2) Все методы этого контроллера будут обрабатывать URL, начинающиеся с /users
public class UserController {

    private final UserService userService;  // (3) Зависимость от сервиса

    @Autowired  // (4) Внедрение зависимости
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // (5) ПОКАЗАТЬ ВСЕХ ПОЛЬЗОВАТЕЛЕЙ + ФОРМУ ДОБАВЛЕНИЯ
    @GetMapping  // (6) GET-запрос на /users
    public String showAllUsers(Model model) {
        // Получаем всех пользователей из сервиса
        List<User> users = userService.getAllUsers();

        // Кладем список пользователей в модель (чтобы отобразить на странице)
        model.addAttribute("users", users);

        // Создаем пустого пользователя для формы добавления
        model.addAttribute("newUser", new User());

        // Возвращаем имя JSP страницы (users.jsp)
        return "users";
    }

    // (7) ДОБАВИТЬ ПОЛЬЗОВАТЕЛЯ
    @PostMapping("/add")  // POST-запрос на /users/add
    public String addUser(@ModelAttribute("newUser") User user) {
        // @ModelAttribute автоматически связывает поля формы с объектом User
        // Сохраняем пользователя через сервис
        userService.saveUser(user);

        // Перенаправляем обратно на список пользователей
        return "redirect:/users";
    }

    // (8) ПОКАЗАТЬ ФОРМУ РЕДАКТИРОВАНИЯ
    @GetMapping("/edit/{id}")  // GET-запрос на /users/edit/123
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // @PathVariable берет id из URL
        // Получаем пользователя по ID
        User user = userService.getUserById(id);

        // Кладем пользователя в модель для формы редактирования
        model.addAttribute("user", user);

        // Возвращаем страницу редактирования
        return "edit-user";
    }

    // (9) ОБНОВИТЬ ПОЛЬЗОВАТЕЛЯ
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    // (10) УДАЛИТЬ ПОЛЬЗОВАТЕЛЯ
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}