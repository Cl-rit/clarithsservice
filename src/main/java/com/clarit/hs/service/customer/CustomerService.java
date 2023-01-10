package com.clarit.hs.service.customer;

import javax.json.JsonPatch;

import com.clarit.hs.controller.IAdminService;
import com.clarit.hs.controller.ICustomerService;
import com.clarit.hs.service.items.Customer;

import com.clarit.hs.service.items.IPropertyCus;
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
			Link selfLink = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(customer.getId()).withSelfRel();
			customer.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers,link);
		return result;
	}





	@Override
	public CollectionModel<Customer> get(String name) {
		// TODO Auto-generated method stub

		List<Customer> customers = iPropertyCus.get(name);
		for(Customer customer : customers) {
			Link selfLink = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(customer.getName()).withSelfRel();
			customer.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers, link);
		return result;
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
		Customer customer1 = iPropertyCus.add(String.valueOf(customer));
		Link link = WebMvcLinkBuilder.linkTo(IAdminService.class).slash(customer).withSelfRel();
		customer1.add(link);
		return customer1;
	}


	@Override
	public Customer patch(JsonPatch jsonPatch) {
		// TODO Auto-generated method stub
		return null;
	}

}
