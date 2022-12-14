package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Service.BookingService;
import com.Nixagh.javaspringmongodb.Service.MotelService;
import com.Nixagh.javaspringmongodb.Service.ReceiptSevice;
import com.Nixagh.javaspringmongodb.model.Booking;
import com.Nixagh.javaspringmongodb.model.Motel;
import com.Nixagh.javaspringmongodb.model.Receipt;
import com.Nixagh.javaspringmongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

  @Autowired
  MotelService motelService;
  @Autowired
  BookingService bookingService;
  @Autowired
  ReceiptSevice receiptsevice;

  @GetMapping("/booking/create/{id}")
  public String getBooking(@PathVariable("id") String id, Model model, Motel motel, User user, Booking booking, HttpSession session) {
    User userSession = (User) session.getAttribute("user");
    if (userSession == null)
      return "redirect:/login";

    model.addAttribute("motel", motelService.getMotelById(id));
    model.addAttribute("user", userSession);
    model.addAttribute("booking", booking);
    return "booking";
  }

  @PostMapping("/booking/create/{id}")
  public String createBooking(@PathVariable String id, Model model, Booking booking, HttpSession session) {
    Motel motel = motelService.getMotelById(id);
    User user = (User) session.getAttribute("user");

    booking.setUserId(user.getId());
    booking.setMotel(motel);
    motel.setStatus("Busy");
    //save
    Booking result = bookingService.addBooking(booking);
    Motel M_result = motelService.updateMotel(motel);

    return "redirect:/hostDetail/" + motel.getHostId();
  }

  @GetMapping("/booking/{id}")
  public String getBooking(Model model, @RequestParam(defaultValue = "0") boolean status, @PathVariable String id) {
    model.addAttribute("status", status);
    model.addAttribute("id", id);
    model.addAttribute("receipts", receiptsevice.findByStatusAndHostId(status, id));
    return "ReceiptManager";
  }

  @PostMapping("/receipt/done/{id}")
  public String receiptDone(@PathVariable String id, Model model, HttpSession session) {
    User userSession = (User) session.getAttribute("user");
    if (userSession == null)
      return "redirect:/login";

    Receipt receipt = receiptsevice.findById(id);
    receipt.setStatus(true);
    receiptsevice.update(receipt);

    return "redirect:/booking/" + userSession.getHostId();
  }

}
