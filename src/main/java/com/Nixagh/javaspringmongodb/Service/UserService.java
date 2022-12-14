package com.Nixagh.javaspringmongodb.Service;

import com.Nixagh.javaspringmongodb.model.User;
import com.Nixagh.javaspringmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getUserById(String id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));
  }

  public void addUser(User user) {
    userRepository.insert(user);
  }

  public void updateUser(User user) {
    userRepository.save(user);
  }

  public void removeUser(String id) {
    userRepository.deleteById(id);
  }

  public User getUserByUsername(String username) {return userRepository.findByUsername(username);}
  public User getUserByEmail(String email) {return userRepository.findByEmail(email);}
}
