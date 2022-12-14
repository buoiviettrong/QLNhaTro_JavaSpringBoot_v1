package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.model.Motel;
import com.Nixagh.javaspringmongodb.model.Order;
import com.Nixagh.javaspringmongodb.model.User;
import com.Nixagh.javaspringmongodb.repository.MotelRepository;
import com.Nixagh.javaspringmongodb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

  @Autowired
  OrderRepository OrderRepository;
  @Autowired
  MotelRepository MotelRepository;

//  @GetMapping("/booking/{id}")
//  public String getBooking(@PathVariable("id") String id, Model model, Motel motel, User user, HttpSession session) {
//    User userSession = (User) session.getAttribute("user");
//    if (userSession == null)
//      return "redirect:/login";
//
//    model.addAttribute("motel", this.MotelRepository.findById(id));
//    model.addAttribute("user", userSession);
//
//    return "booking";
//  }
//
//  @PostMapping("/booking/{id}")
//  public String booking(Model model, Order order) {
//
//    this.OrderRepository.save(order);
//    model.addAttribute("message", "Đặt Phòng Thành Công");
//    return "home";
//  }


}
