package com.clarit.hs.service.customer;
import com.clarit.hs.controller.ICustomerService;
import com.clarit.hs.service.exception.ItemNotFoundException;
import com.clarit.hs.service.exception.UnAuthorizedException;
import com.clarit.hs.service.items.Customer;
import com.clarit.hs.service.items.IPropertyCus;
import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import com.github.fge.jsonpatch.JsonPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

	public static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	IPropertyCus iPropertyCus;



	@Override
	public CollectionModel<Customer> getAll(String id) {
		List<Customer> customers = iPropertyCus.getAll(id);
		for (Customer customer : customers) {
			Link selfLink = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer.getId()).withSelfRel();
			customer.add(selfLink);
		}
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers, link);

		return result;
	}




	@Override
	public CollectionModel<Customer> get(String name) {
		logger.info("Getting username - {}", name);
		List<Customer> customers =  iPropertyCus.get(name);
		for(Customer customer : customers) {
			Link selfLink = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer.getName()).withSelfRel();
			customer.add(selfLink);
		}

		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).withSelfRel();
		CollectionModel<Customer> result = CollectionModel.of(customers, link);
		logger.info("successfully authenticated");
		return result;

	}

	@Override
	public Customer delete(String name) {
		logger.info("delete User - {}",name);
		Customer customer = iPropertyCus.delete(name);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer).withSelfRel();
		customer.add(link);
		logger.info("details Deleted");
		return null;
	}

	@Override
	public Customer update(Customer customer) {
		logger.info("details updating - {}",customer);
		Customer customer1 = iPropertyCus.update(customer);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer1.getName()).withSelfRel();
		customer1.add(link);
		logger.info("Successfully updated");
		return customer1;

	}

	@Override
	public Customer add(Customer customer) {
		logger.info("details stored - {} ",customer);
		Customer customer1 = iPropertyCus.add(customer);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer1.getName()).withSelfRel();
		customer1.add(link);
		logger.info("successfully added");
		return customer1;
	}

	@Override
	public Customer patch( String name,JsonPatch jsonPatch) {
		Customer customer1 = iPropertyCus.patch(name,jsonPatch);
		Link link = WebMvcLinkBuilder.linkTo(ICustomerService.class).slash(customer1.getName()).withSelfRel();
		customer1.add(link);
		return customer1;
	}


}
