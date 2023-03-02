package com.clarit.hs.service.admin;

import java.util.List;

import com.clarit.hs.service.items.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.clarit.hs.controller.IAdminService;
import com.clarit.hs.service.items.IProperty;


/**
 * Created by mnachiappan on 1/4/23.
 */
@Service
public class AdminService  implements IAdminService {
	

	@Autowired
	IProperty property;

	@Override
	public CollectionModel<Room> getAll(boolean isOccupied) {
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
	public Room book(Room room) {
		Room room1 =  property.book(room);
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(room1.getNumber()).withSelfRel();
		room1.add(link);
		return room1;
	}

	@Override
	public CollectionModel<Room>  get(int number) {
		List<Room> rooms = property.get(number);
		for(Room room : rooms) {
	        Link selfLink = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(room.getNumber()).withSelfRel();
	        room.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).withSelfRel();
	    CollectionModel<Room> result = CollectionModel.of(rooms, link);
	    return result;
	}

	@Override
	public Room cancelBooking(int number) {
		Room room1 =  property.cancelBooking(number);
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(number).withSelfRel();
		room1.add(link);
		return null;
		
	}
}
