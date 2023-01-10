package com.clarit.hs.service.items;

import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyCus implements IPropertyCus{


    @Autowired
    ItemRepositoryCus itemRepositoryCus;

    @Override
    public List<Customer> getAll(String id){
        return getAllCustomers();
    }

    @Override
    public Customer add(String customerName){
        return bookCustomer();
    }


    @Override
    public List<Customer> get(String customerName){
        return itemRepositoryCus.findByName(customerName);
    }



   private List<Customer> getAllCustomers(){
        return itemRepositoryCus.findAll();
   }

    private Customer bookCustomer(){
        Customer customer = new Customer();
        customer.setName("jerald");
        customer.setEmail("jero@gmail.com");
        customer.setAddress("senjai");
        customer.setId("jero06");
        customer.setPhone("9878877690");
        return itemRepositoryCus.save(customer);

    }
}
