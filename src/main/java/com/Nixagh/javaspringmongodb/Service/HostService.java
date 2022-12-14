package com.Nixagh.javaspringmongodb.Service;

import com.Nixagh.javaspringmongodb.model.Host;
import com.Nixagh.javaspringmongodb.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HostService {
  @Autowired
  HostRepository hostRepository;

  public List<Host> getAll() {
    return hostRepository.findAll();
  }

  public Host getHostById(String id) {
    return hostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));
  }

  public Host addHost(Host host) {
    return hostRepository.insert(host);
  }

  public void updateHost(Host host) {
    hostRepository.save(host);
  }

  public void removeHost(String id) {
    hostRepository.deleteById(id);
  }

  public Map<String, Object> getHostInPageHome(String province, String district, String wand, String keyword, int page, int pageSize, String sortBy) {
    Map<String, Object> response = new HashMap<String, Object>();
    Sort sort = Sort.by(sortBy);
    Pageable reqPage = PageRequest.of(page, pageSize, sort);
    Page<Host> hostPage;

    if(province != null && !province.equals("")|| keyword != null && !keyword.equals("")) {
      hostPage = hostRepository.findByAddressProvinceLikeAndAddressDistrictLikeAndAddressWandLikeAndNameLikeIgnoreCaseAndPostTrue(province, district, wand, keyword, reqPage);
    }
    else {
      hostPage = hostRepository.findAllByPostTrue(reqPage);
    }

    if ((int) hostPage.getTotalPages() > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, (int) hostPage.getTotalPages())
              .boxed()
              .collect(Collectors.toList());
      response.put("pageNumbers", pageNumbers);
    }

    response.put("data", hostPage.getContent());
    response.put("totalPages", hostPage.getTotalPages());
    response.put("totalElements", hostPage.getTotalElements());
    response.put("currentPage", hostPage.getNumber());
    return response;
  }

  public List<Host> getALlByExample(Host host) {
    ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.REGEX));
    Example<Host> h = Example.of(host, matcher);
    return hostRepository.findAll(h);
  }
}
