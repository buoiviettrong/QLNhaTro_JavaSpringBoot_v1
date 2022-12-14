package com.Nixagh.javaspringmongodb.repository;

import com.Nixagh.javaspringmongodb.model.Host;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends MongoRepository<Host, String> {
  Page<Host> findAllByPostTrue(Pageable pageable);

  Page<Host> findByAddressProvinceLikeAndAddressDistrictLikeAndAddressWandLikeAndNameLikeIgnoreCaseAndPostTrue(String province, String district, String wand, String keyword,Pageable pageable);
}
