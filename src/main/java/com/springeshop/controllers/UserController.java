package com.springeshop.controllers;

import com.springeshop.data.dto.UserDTO;
import com.springeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO)) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user-list";
    }

}
