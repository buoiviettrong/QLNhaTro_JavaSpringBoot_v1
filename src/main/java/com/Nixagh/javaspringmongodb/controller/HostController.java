package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Helpers.UploadImage;
import com.Nixagh.javaspringmongodb.Service.HostService;
import com.Nixagh.javaspringmongodb.Service.MotelService;
import com.Nixagh.javaspringmongodb.Service.UserService;
import com.Nixagh.javaspringmongodb.model.Enum.ERole;
import com.Nixagh.javaspringmongodb.model.Enum.EStatus;
import com.Nixagh.javaspringmongodb.model.Helpers.Address;
import com.Nixagh.javaspringmongodb.model.Host;
import com.Nixagh.javaspringmongodb.model.Motel;
import com.Nixagh.javaspringmongodb.model.User;
import com.Nixagh.javaspringmongodb.repository.HostRepository;
import com.Nixagh.javaspringmongodb.repository.MotelRepository;
import com.Nixagh.javaspringmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HostController {

  @Autowired
  UserService userService;
  @Autowired
  HostService hostService;
  @Autowired
  MotelService motelService;

  @GetMapping("/create-host")
  public String showCreateForm(HttpSession session, Host host, Address address) {
    User check = (User) session.getAttribute("user");
    if (check == null) {
      return "redirect:/login";
    }

    if (check.getHostId() != null && !check.getHostId().equals("")) {
      return "waitingPage";
    }

    return "create-host";
  }

  @PostMapping("/create-host")
  public String create(@RequestParam("file") MultipartFile file,
                       Host host,
                       Address address,
                       BindingResult result,
                       Model model,
                       HttpSession session) throws IOException {
    if (result.hasErrors())
      return "create-host";

    host.setAddress(address);
    host.setImage(UploadImage.upload(file));
    Host h = hostService.addHost(host);

    User user = (User) session.getAttribute("user");
    user.setHostId(h.getId());
    userService.updateUser(user);

    model.addAttribute("host", host);
    model.addAttribute("address", address);
    return "redirect:/create-host";
  }

  @GetMapping("/hostDetail/{id}")
  public String showHostDetail(@PathVariable("id") String id,
                               @RequestParam(name = "page", defaultValue = "0") Integer page,
                               @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                               @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                               @RequestParam(name = "minA", defaultValue = "0") Integer minA,
                               @RequestParam(name = "maxA", defaultValue = "9999") Integer maxA,
                               @RequestParam(name = "minP", defaultValue = "0") Integer minP,
                               @RequestParam(name = "maxP", defaultValue = "99999999") Integer maxP,
                               @RequestParam(name = "motelType", defaultValue = "") String motelType,
                               Model model,
                               Host host) {
    Map<String, Object> response = motelService.getMotelInPageByHostId(minA, maxA, minP, maxP, motelType, "Space", id, page, pageSize, sortBy);

    model.addAttribute("motels", response.get("data"));
    model.addAttribute("host", this.hostService.getHostById(id));
    model.addAttribute("currentPage", response.get("currentPage"));
    model.addAttribute("totalPages", response.get("totalPages"));
    model.addAttribute("pageNumbers", response.get("pageNumbers"));
    return "hostDetail";
  }

  @GetMapping("/host/{id}")
  public String showManager(@PathVariable("id") String id,
                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                            @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                            @RequestParam(name = "minA", defaultValue = "0") Integer minA,
                            @RequestParam(name = "maxA", defaultValue = "9999") Integer maxA,
                            @RequestParam(name = "minP", defaultValue = "0") Integer minP,
                            @RequestParam(name = "maxP", defaultValue = "99999999") Integer maxP,
                            @RequestParam(name = "motelType", defaultValue = "") String motelType,
                            @RequestParam(name = "status", defaultValue = "") String status,
                            Model model,
                            HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != ERole.Host) {
      model.addAttribute("message", "Bạn không có quyền vào trang này");
      return "redirect:/home";
    }
    Map<String, Object> response = motelService.getMotelInPageByHostId(minA, maxA, minP, maxP, motelType, status, id, page, pageSize, sortBy);

    model.addAttribute("motels", response.get("data"));
    model.addAttribute("host", hostService.getHostById(id));
    model.addAttribute("currentPage", response.get("currentPage"));
    model.addAttribute("totalPages", response.get("totalPages"));
    model.addAttribute("pageNumbers", response.get("pageNumbers"));
    return "hostManager";
  }
}
