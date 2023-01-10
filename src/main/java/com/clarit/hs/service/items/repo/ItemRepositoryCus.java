package com.clarit.hs.service.items.repo;

import com.clarit.hs.service.items.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCus extends MongoRepository<Customer,String> {

    @Query(value="{name:?0}")
    List<Customer> findByName(String name);

    @Query(value="{isOccupied:'?0'}")
    List<Customer> findAll(boolean isOccupied);


}
