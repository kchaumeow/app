package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anime.app.Models.User;
import ru.anime.app.Services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute ("userForm") User user, Model model){
        boolean res = userService.createUser(user);
        if (!res){
            model.addAttribute("errorMessage", "Пользователь с email " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/auth_home";
    }
}