package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);

  User findByEmail(String email);

  User getByUsername(String username);
}
