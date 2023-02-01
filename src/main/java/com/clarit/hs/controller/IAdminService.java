package com.clarit.hs.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import com.clarit.hs.service.items.Room;

import javax.websocket.server.PathParam;

/**
 * Created by mnachiappan on 1/4/23.
 */
@RestController
@RequestMapping(value = "/rooms")
public interface IAdminService {

	@GetMapping(produces = { "application/hal+json" })
	public CollectionModel<Room> getAll(boolean occupied);
	
	@PostMapping(produces = { "application/hal+json" })
	public Room book(@RequestBody Room room);

	@GetMapping(value = "/{number}")
	public CollectionModel<Room>  get(@PathVariable int number);
	
	@DeleteMapping(value = "/{number}")
	public void cancelBooking(@PathVariable int number);



}
