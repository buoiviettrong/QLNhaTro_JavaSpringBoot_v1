package com.Nixagh.javaspringmongodb.Service;

import com.Nixagh.javaspringmongodb.model.Receipt;
import com.Nixagh.javaspringmongodb.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptSevice {
  @Autowired
  ReceiptRepository receiptRepository;
  public Receipt addReceipt(Receipt receipt) {
    return receiptRepository.save(receipt);
  }

  public List<Receipt> findByStatusAndHostId(boolean status, String hostId) {
    return receiptRepository.findByStatusAndHostId(status, hostId);
  }

  public Receipt findById(String id) {
    return receiptRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));
  }

  public void update(Receipt receipt) {
    receiptRepository.save(receipt);
  }

  public List<Receipt> findByUserIdAndStatus(String id, boolean status) {
    return receiptRepository.findByBooking_UserIdAndStatus(id, status);
  }
}
