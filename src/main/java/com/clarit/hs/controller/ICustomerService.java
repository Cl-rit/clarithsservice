package com.clarit.hs.controller;

import java.util.List;

import javax.json.JsonPatch;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import com.clarit.hs.service.items.Customer;


@RestController
@RequestMapping(value = "/customers")
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
	
	@PostMapping(produces = { "application/hal+json" })
	public Customer add(@RequestBody Customer customer);
	
	@PatchMapping (value="/{name}", produces = { "application/hal+json" })
	public Customer patch(@RequestBody JsonPatch jsonPatch);
	

}
