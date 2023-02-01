package com.clarit.hs.controller;

import com.clarit.hs.service.items.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/customers")
public interface ICustomerService {


	
	//Must implement pagination
	@GetMapping(produces = { "application/hal+json" })
	public CollectionModel<Customer> getAll( String id);

	@GetMapping(value="/{name}", produces = { "application/hal+json" })
	public CollectionModel<Customer> get(@PathVariable String name);
	
	@DeleteMapping(value="/{name}", produces = { "application/hal+json" })
	public void delete(@PathVariable String name);
	
	@PutMapping(value="/{name}", produces = { "application/hal+json" })
	public Customer update(@RequestBody Customer customer);
	
	@PostMapping(produces = { "application/hal+json" })
	public Customer add(@RequestBody Customer customer);

	@PatchMapping(value = "/{name}",produces = {"application/json-patch+json"})
	 public Customer patch(@RequestBody JsonPatch jsonPatch, @PathVariable String name) throws JsonPatchException, JsonProcessingException;




}
