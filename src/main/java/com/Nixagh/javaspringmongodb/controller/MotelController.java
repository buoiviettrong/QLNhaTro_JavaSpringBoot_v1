package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Helpers.UploadImage;
import com.Nixagh.javaspringmongodb.Service.BookingService;
import com.Nixagh.javaspringmongodb.Service.HostService;
import com.Nixagh.javaspringmongodb.Service.MotelService;
import com.Nixagh.javaspringmongodb.Service.ReceiptSevice;
import com.Nixagh.javaspringmongodb.model.*;
import com.Nixagh.javaspringmongodb.model.Enum.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MotelController {

  @Autowired
  HostService hostService;
  @Autowired
  MotelService motelService;
  @Autowired
  BookingService bookingService;
  @Autowired
  ReceiptSevice recieptSevice;

  @GetMapping("/host/add-motel")
  public String showAddMotelForm(Model model,
                                 Motel motel,
                                 HttpSession session) {
    User user = (User) session.getAttribute("user");
    if(user == null || user.getRole() == ERole.User) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }

    model.addAttribute("motel", motel);
    model.addAttribute("hostId", user.getHostId());
    return "add-motel";
  }

  @PostMapping("/host/add-motel")
  public String addMotel(@RequestParam("file") MultipartFile file,
                         Model model,
                         Motel motel,
                         HttpSession session) throws IOException {
    // Validate
    motel.setImage(UploadImage.upload(file));
    User user = (User) session.getAttribute("user");
    if(user == null || user.getRole() == ERole.User) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }
//    Motel m = this.MotelRepository.save(motel);
    Host host = hostService.getHostById(user.getHostId());

    List<Motel> motels = motelService.getMotelsByHostId(host.getId());

    if(motels.size() == 0) {
      host.setMinPrice((int) motel.getPrice());
      host.setMaxPrice((int) motel.getPrice());
    }
    else {
      if (host.getMinPrice() > motel.getPrice())
        host.setMinPrice((int) motel.getPrice());
      if (host.getMaxPrice() < motel.getPrice())
        host.setMaxPrice((int) motel.getPrice());
    }

    motel.setHostId(user.getHostId());
    Motel result = motelService.addMotel(motel);
    hostService.updateHost(host);
    model.addAttribute("motel", result);
    model.addAttribute("hostId", user.getHostId());
    model.addAttribute("message", "Thêm Phòng Mới Thành Công");
    return "add-motel";
  }

  @GetMapping("/host/delete-motel/{id}")
  public String deleteMotel(@PathVariable String id,
                            Model model,
                            Motel motel,
                            HttpSession session) {
    User user = (User) session.getAttribute("user");
    if(user == null || user.getRole() == ERole.User) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }

    motelService.removeMotel(id);
    model.addAttribute("message", "Xóa Nhà trọ thành công");
    return "redirect:/host/"+user.getHostId();
  }

  @GetMapping("/motel/{id}")
  public String getMotel(@PathVariable String id,
                         Model model,
                         HttpSession session) {
    User user = (User) session.getAttribute("user");
    if(user == null || user.getRole() == ERole.User) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }

    model.addAttribute("booking", bookingService.getBooking(id));
    model.addAttribute("motel", motelService.getMotelById(id));
    return "motelManager";
  }

  @PostMapping("/motel/checkout/{id}")
  public String checkout(@PathVariable String id, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if(user == null || user.getRole() == ERole.User) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }

    Booking booking = bookingService.getBooking(id);
    Motel motel = motelService.getMotelById(id);
    motel.setStatus("Space");

    Receipt receipt = new Receipt();
    receipt.setBooking(booking);
    receipt.sethostId(user.getHostId());
    Receipt result =  recieptSevice.addReceipt(receipt);

    bookingService.delete(booking);
    Motel M_result = motelService.updateMotel(motel);

    return "redirect:/host/" + motel.getHostId();
  }

}
