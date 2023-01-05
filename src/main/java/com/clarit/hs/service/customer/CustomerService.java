package com.clarit.hs.service.customer;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonPatch;

import com.clarit.hs.controller.ICustomerService;
import com.clarit.hs.service.items.Customer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

	@Override
	public CollectionModel<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer add(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer patch(JsonPatch jsonPatch) {
		// TODO Auto-generated method stub
		return null;
	}

}
