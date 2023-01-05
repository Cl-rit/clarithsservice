package com.clarit.hs.service.admin;

import com.clarit.hs.controller.IAdminService;
import com.clarit.hs.service.items.IProperty;
import com.clarit.hs.service.items.Room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mnachiappan on 1/4/23.
 */
@Service
public class AdminService implements IAdminService {
	
	@Autowired
	IProperty property;

	@Override
	public List<Room> getAll(boolean occupied) {
		return property.getAll(false);
	}

	@Override
	public Room book(Integer number) {
		// TODO Auto-generated method stub
		return property.book(number);
	}

	@Override
	public Room get(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBooking(int number) {
		// TODO Auto-generated method stub
		
	}
}
