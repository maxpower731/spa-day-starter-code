package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.launchcode.spaday.data.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping()
    public String processAddUserForm(Model model, @ModelAttribute User user, String verifyPassword) {
        // add form submission handling code here
        if (user.getPassword().equals(verifyPassword)) {
            UserData.add(user);
            model.addAttribute("name", user.getUsername());
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        }
        model.addAttribute("error", "Passwords do not match.");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "user/add";
    }

    @GetMapping()
    public String displayListOfUsers(Model model) {
        model.addAttribute("users", UserData.getAll());

        return "user/index";
    }

    @GetMapping("userDetail/{id}")
    public String getUserDetails(Model model, @PathVariable Integer id) {
        model.addAttribute("user", UserData.getById(id));

        return "user/userDetail";
    }

}
