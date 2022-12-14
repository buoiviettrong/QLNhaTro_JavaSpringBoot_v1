package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
