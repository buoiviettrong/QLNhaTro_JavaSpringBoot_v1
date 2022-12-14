package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Service.UserService;
import com.Nixagh.javaspringmongodb.model.User;
import com.Nixagh.javaspringmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenController {
  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public String showLoginForm(User user, HttpSession session) {
    User x = (User) session.getAttribute("user");
    return "login";
  }

  @PostMapping("/login")
  public String login(User user, BindingResult result, Model model, HttpSession session, String error) {
    if (result.hasErrors()) {
      return "login";
    }
    if (user.getPassword().equals("") || user.getUsername().equals("")) {
      model.addAttribute("error", "Please enter your Info");
      return "login";
    }
    User check = this.userService.getUserByUsername(user.getUsername());

    if (check == null) {
      model.addAttribute("error", "Username not found");
      return "login";
    }
    if (!check.getPassword().equals(user.getPassword())) {
      model.addAttribute("error", "Password not correct");
      return "login";
    }

    model.addAttribute("user", check);
    session.setAttribute("user", check);
    return "redirect:/home";
  }

  @GetMapping("/register")
  public String showRegisterForm(User user) {
    return "register";
  }

  @PostMapping("/register")
  public String register(User user, BindingResult result, Model model, HttpSession session) {
    if (this.userService.getUserByUsername(user.getUsername()) != null
            || this.userService.getUserByEmail(user.getEmail()) != null) {
      model.addAttribute("errors", "Username already exists");
      return "register";
    }
    this.userService.addUser(user);
//    model.addAttribute("user", user);
    session.setAttribute("user", user);
    return "redirect:/home";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("user");
    return "redirect:/home";
  }

}
