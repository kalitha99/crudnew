package com.example.democrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class usercontroller {
    @Autowired
    private userservice service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<user> listusers = service.listAll();
        model.addAttribute("listusers", listusers);
        return "users";
    }

    @GetMapping("/users/new")
    public String adduser(Model model) {
        model.addAttribute("user", new user());
        model.addAttribute("pageTitle", "Add new user");
        return "userform";
    }

    @PostMapping("/users/save")
    public String saveuser(user user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message","Successfully saved");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String edituser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            user user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user (ID:" + id + ")");
            ra.addFlashAttribute("message","Successfully saved");
            return "userform";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteuser(@PathVariable("id") Integer id,RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("message","Successfully deleted");
        return "redirect:/users";

    }
}
