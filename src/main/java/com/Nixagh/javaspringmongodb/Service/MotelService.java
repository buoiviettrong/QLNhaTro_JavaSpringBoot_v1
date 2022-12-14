package com.Nixagh.javaspringmongodb.Service;

import com.Nixagh.javaspringmongodb.Helpers.createPageNumbers;
import com.Nixagh.javaspringmongodb.model.Enum.EStatus;
import com.Nixagh.javaspringmongodb.model.Motel;
import com.Nixagh.javaspringmongodb.repository.MotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MotelService {

  @Autowired
  MotelRepository motelRepository;

  public List<Motel> getAll() {
    return motelRepository.findAll();
  }

  public Motel addMotel(Motel motel) {
    return motelRepository.insert(motel);
  }

  public Motel updateMotel(Motel motel) {
    return motelRepository.save(motel);
  }

  public void removeMotel(String id) {
    motelRepository.deleteById(id);
  }

  public Motel getMotelById(String id) {return motelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));}
  public List<Motel> getMotelsByHostId(String hostId) {return motelRepository.findAllByHostId(hostId);}

  public Map<String, Object> getMotelInPageByHostId(Integer minA, Integer maxA, Integer minP, Integer maxP, String motelType, String status,
                                                    String hostId, int page, int pageSize, String sortBy) {
    Map<String, Object> response = new HashMap<String, Object>();
    Sort sort = Sort.by(sortBy);
    Pageable reqPage = PageRequest.of(page, pageSize, sort);

    status = ".*"+status+".*";
    motelType = ".*"+motelType+".*";
    Page<Motel> motelPage = motelRepository.findByFilter(status, motelType, minP, maxP, minA, maxA, hostId, reqPage);


    response.put("pageNumbers", createPageNumbers.createPageNumbers(motelPage.getTotalPages()));

    response.put("data", motelPage.getContent());
    response.put("totalPages", motelPage.getTotalPages());
    response.put("totalElements", motelPage.getTotalElements());
    response.put("currentPage", motelPage.getNumber());
    return response;
  }
}
