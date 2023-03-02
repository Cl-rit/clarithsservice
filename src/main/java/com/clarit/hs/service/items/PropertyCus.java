package com.clarit.hs.service.items;

import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    public Customer add(Customer customer){
        return bookCustomer(customer);
    }

    @Override

    public Customer delete(String name) {

        return itemRepositoryCus.deleteAll(name);
    }

    @Override

    public Customer update(Customer customer) {
        return updateCustomer(customer);
    }

    @Override

    public List<Customer> get(String customerName){
        return itemRepositoryCus.findByName(customerName);
    }

    private List<Customer> getAllCustomers(){
        return itemRepositoryCus.findAll();
    }

    private Customer bookCustomer(Customer customer){

        return itemRepositoryCus.save(customer);

    }
    private Customer updateCustomer(Customer customer){

        return itemRepositoryCus.save(customer);
    }

    @Override
    public Customer patch(String name, JsonPatch jsonPatch){

       Customer customer = (Customer) itemRepositoryCus.findName(name);
        Customer customer1 =  applyPatchToCustomer(jsonPatch,  customer);
        return itemRepositoryCus.save(customer1);
    }

    @Autowired
    ObjectMapper objectMapper;
    @SneakyThrows
    private Customer applyPatchToCustomer(JsonPatch jsonPatch, Customer customer) {
        JsonNode patched= jsonPatch.apply(objectMapper.convertValue(customer, JsonNode.class));
        return objectMapper.treeToValue(patched, Customer.class);

    }
}