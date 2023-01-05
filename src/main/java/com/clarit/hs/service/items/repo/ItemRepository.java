package com.clarit.hs.service.items.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.clarit.hs.service.items.Room;

public interface ItemRepository extends MongoRepository<Room, String> {
    
    @Query("{number:'?0'}")
    Room findItemByNumber(String number);
    
    @Query(value="{isOccupied:'?0'}")
    List<Room> findAll(boolean isOccupied);
    
    public long count();

}