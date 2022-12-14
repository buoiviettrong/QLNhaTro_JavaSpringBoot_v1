package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Service.BookingService;
import com.Nixagh.javaspringmongodb.Service.ReceiptSevice;
import com.Nixagh.javaspringmongodb.model.Booking;
import com.Nixagh.javaspringmongodb.model.Receipt;
import com.Nixagh.javaspringmongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReceiptController {
  @Autowired
  ReceiptSevice receiptSevice;
  @Autowired
  BookingService bookingService;

  @GetMapping("/receipts")
  public String getReceipt(Model model, HttpSession session, @RequestParam(value = "status", defaultValue = "0") boolean status) {
    User user = (User)session.getAttribute("user");

    Booking booking = bookingService.findByUserId(user.getId());
    List<Receipt> receipts = receiptSevice.findByUserIdAndStatus(user.getId(), status);

    model.addAttribute("booking", booking);
    model.addAttribute("receipts", receipts);
    model.addAttribute("status", status);
    return "Receipt";
  }

}
