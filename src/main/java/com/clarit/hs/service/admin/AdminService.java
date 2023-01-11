package com.clarit.hs.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.clarit.hs.controller.IAdminService;
import com.clarit.hs.service.exception.ItemNotFoundException;
import com.clarit.hs.service.items.IProperty;
import com.clarit.hs.service.items.Room;

/**
 * Created by mnachiappan on 1/4/23.
 */
@Service
public class AdminService  implements IAdminService {
	

	@Autowired
	IProperty property;

	@Override
	public CollectionModel<Room> getAll(boolean occupied) {
		List<Room> rooms = property.getAll(false);
		for(Room room : rooms) {
	        Link selfLink = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(room.getNumber()).withSelfRel();
	        room.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).withSelfRel();
	    CollectionModel<Room> result = CollectionModel.of(rooms, link);
	    return result;
	}


	@Override
	public Room book(Integer number) {
		Room room =  property.book(number);
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(number).withSelfRel();
		room.add(link);
		return room;
	}

	@Override
	public CollectionModel<Room>  get(int number) {
		List<Room> rooms = property.get(number);
		if(CollectionUtils.isEmpty(rooms)) {
			throw new ItemNotFoundException("Unable to find entity", "Pass a valid room number and try again");
		}
		for(Room room : rooms) {
	        Link selfLink = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(room.getNumber()).withSelfRel();
	        room.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).withSelfRel();
	    CollectionModel<Room> result = CollectionModel.of(rooms, link);
	    return result;
	}

	@Override
	public void cancelBooking(int number) {
		// TODO Auto-generated method stub
		List<Room> rooms = property.get(number);
		rooms.remove(rooms);
		
	}
}
