package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository usersDao;

    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm, @RequestParam("email") String email) {
        if (password.equals(passwordConfirm)) {
            User newUser = new User(username, password, email);
            usersDao.save(newUser);
            return "/posts/index";
        } else {
            return "register";
        }

    }


}
