package com.clarit.hs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clarit.hs.service.items.Room;

/**
 * Created by mnachiappan on 1/4/23.
 */
@RestController
public interface IAdminService {

	@GetMapping(value = "/rooms")
	public List<Room> getAll(boolean occupied);
	
	@PostMapping(value = "/room/{number}")
	public Room book(@PathVariable Integer number);
	
	@GetMapping(value = "/room/{number}")
	public Room get(@PathVariable int number);
	
	@DeleteMapping(value = "room/{number}")
	public void cancelBooking(int number);
}
