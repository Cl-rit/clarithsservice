package com.clarit.hs.service.customer;

import com.clarit.hs.controller.ICustomerService;
import com.clarit.hs.service.items.Customer;
import com.clarit.hs.service.items.IPropertyCus;
import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	IPropertyCus iPropertyCus;

	@Override
	public CollectionModel<Customer> getAll(String id) {

		List<Customer> customers = iPropertyCus.getAll(id);
		for (Customer customer : customers ){
			Link selfLink = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer.getId()).withSelfRel();
			customer.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers,link);
		return result;
	}





	@Override
	public CollectionModel<Customer> get(String name) {


		List<Customer> customers = iPropertyCus.get(name);
		for(Customer customer : customers) {
			Link selfLink = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer.getName()).withSelfRel();
			customer.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers, link);
		return result;
	}

	@Override
	public void delete(String name) {

		iPropertyCus.delete(name);
	}

	@Override
	public Customer update(Customer customer) {

		Customer customer1 = iPropertyCus.update(customer);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer1).withSelfRel();
		customer1.add(link);

		return null;
	}

	@Override
	public Customer add(Customer customer) {
		Customer customer1 = iPropertyCus.add(customer);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer1).withSelfRel();
		customer1.add(link);
		return customer1;
	}

	@Override
	public Customer patch(JsonPatch jsonPatch, String name) throws JsonPatchException, JsonProcessingException {
		Customer customer1 = iPropertyCus.patch(jsonPatch, name);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(name).withSelfRel();
		customer1.add(link);

		return customer1;
	}

}
