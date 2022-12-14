package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.Enum.EStatus;
import com.Nixagh.javaspringmongodb.model.Motel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MotelRepository extends MongoRepository<Motel, String> {
  Page<Motel> findByHostId(String hostId, Pageable pageable);
  List<Motel> findAllByHostId(String hostId);

  @Query("{$and: [{status: {$regex: ?0}}, {type:{$regex: ?1}}, " +
          "{$and : [{price: {$gte: ?2}}, {price : {$lte: ?3}}]}, " +
          "{$and: [{area:{$gte:?4},}, {area:{$lte:?5}}]}" +
          ", {hostId: {$eq: ?6}}]}")
  Page<Motel> findByFilter(String status, String type, int minPrice, int maxPrice, int minArea, int maxArea, String hostId, Pageable pageable);

}

