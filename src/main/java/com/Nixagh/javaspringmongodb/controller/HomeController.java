package com.Nixagh.javaspringmongodb.controller;

import com.Nixagh.javaspringmongodb.Service.HostService;
import com.Nixagh.javaspringmongodb.model.Helpers.Address;
import com.Nixagh.javaspringmongodb.model.Host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

  @Autowired
  private HostService hostService;

  @GetMapping({"/", "home", "search"})
  public String showHome(Host host, Model model,
                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                         @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                         @Param("province") String province,
                         @Param("district") String district,
                         @Param("wand") String wand,
                         @Param("keywords") String keywords) {

    Map<String, Object> response = hostService.getHostInPageHome(province, district, wand, keywords, page, pageSize, sortBy);
    Address address = new Address(province, district, wand);
    model.addAttribute("address", address);
    model.addAttribute("keywords", keywords);
    model.addAttribute("hosts", response.get("data"));
    model.addAttribute("currentPage", response.get("currentPage"));
    model.addAttribute("totalPages", response.get("totalPages"));
    model.addAttribute("pageNumbers", response.get("pageNumbers"));
    return "home";
  }
}
