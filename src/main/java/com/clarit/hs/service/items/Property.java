package com.clarit.hs.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarit.hs.service.items.repo.ItemRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by mnachiappan on 1/4/23.
 */
@Service
public class Property implements IProperty {
	@Autowired
	ItemRepository itemRepository;
    @Override
    public List<Room> getAll(boolean occupied) {
        return getAllRooms();
    }

    @Override
    public Room book(int roomNumber) {
        return bookRoom();
    }

    @Override
    public List<Room> get(int roomNumber) {
        return itemRepository.findByNumber(roomNumber);
    }

    @Override
    public void cancelBooking(int roomNumber) {

    }
    
    private List<Room> getAllRooms(){
    	return itemRepository.findAll();
    }
    
    private Room bookRoom() {
    	Room room = new Room();
        room.setAvailbility(new Date());
        room.setIsOccupied(false);
        room.setNumber(123);
        room.setRoomType(RoomType.DOUBLE);
        return itemRepository.save(room);
    }
}
