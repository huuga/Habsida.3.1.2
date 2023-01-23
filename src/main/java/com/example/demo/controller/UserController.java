package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @RequestMapping(value = "/")
    public ModelAndView showUsers(Model model) {
        model.addAttribute("usersList", userServiceImp.getUsersList());
        return new ModelAndView("index.html");
    }

    @RequestMapping(value = "/add")
    public ModelAndView addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("add-user.html");
    }

    @RequestMapping("/register")
    public ModelAndView submitForm(@ModelAttribute("user") User user) {
        userServiceImp.addUser(user);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(Model model, @PathVariable("id") String userId) {
        try {
            User editableUser = userServiceImp.findUserById(Long.parseLong(userId));
            model.addAttribute("editableUser", editableUser);
            return new ModelAndView("user-edit");
        } catch (NumberFormatException nfe) {
            return new ModelAndView("404");
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveEditedUser(@ModelAttribute("editableUser") User editedUser,
                                 @PathVariable("id") String userId) {
        try {
            editedUser.setId(Long.parseLong(userId));
            userServiceImp.updateUser(editedUser);
            return new ModelAndView("redirect:/");
        } catch (NumberFormatException nfe) {
            return new ModelAndView("404");
        }
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") String userId) {
        userServiceImp.removeUser(Long.parseLong(userId));
        return new ModelAndView("redirect:/");
    }
}
