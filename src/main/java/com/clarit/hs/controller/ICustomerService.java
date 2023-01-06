package com.clarit.hs.controller;

import java.util.List;

import javax.json.JsonPatch;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clarit.hs.service.items.Customer;


@RestController
@RequestMapping(value = "/rooms")
public interface ICustomerService {
	
	//Must implement pagination
	@GetMapping(produces = { "application/hal+json" })
	public CollectionModel<Customer> getAll();
	
	@GetMapping(value="/{name}", produces = { "application/hal+json" })
	public Customer get(String name);
	
	@DeleteMapping(value="/{name}", produces = { "application/hal+json" })
	public void delete(String name);
	
	@PutMapping(value="/{name}", produces = { "application/hal+json" })
	public Customer update(@RequestBody Customer customer);
	
	@GetMapping(produces = { "application/hal+json" })
	public Customer add(@RequestBody Customer customer);
	
	@PutMapping(value="/{name}", produces = { "application/hal+json" })
	public Customer patch(@RequestBody JsonPatch jsonPatch);
	

}
