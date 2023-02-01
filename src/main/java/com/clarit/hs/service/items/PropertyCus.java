package com.clarit.hs.service.items;

import com.clarit.hs.service.exception.ItemNotFoundException;
import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.jayway.jsonpath.internal.filter.ValueNode;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
        return addCustomer(customer);
    }

    @Override
    public Customer update(Customer customer){
        return updateCustomer(customer);
    }

    @Override
    public List<Customer> get(String customerName){

        return itemRepositoryCus.findByName(customerName);
    }

    @Override
    public void delete(String name){
        itemRepositoryCus.delete( itemRepositoryCus.deleteByName(name));
    }
    private List<Customer> getAllCustomers(){
        return itemRepositoryCus.findAll();
    }

    private Customer addCustomer(Customer customer){

        return itemRepositoryCus.save(customer);
    }

    private Customer updateCustomer(Customer customer){
        return itemRepositoryCus.save(customer);
    }


    @Autowired
    ObjectMapper objectMapper;
    @Override
    public Customer patch(JsonPatch jsonPatch, String name) throws JsonPatchException, JsonProcessingException {

                Customer customer = itemRepositoryCus.findByName2(name);
                Customer customer1 = applyPatchToCustomer(jsonPatch, customer);
               return itemRepositoryCus.save(customer1);
    }

    public Customer applyPatchToCustomer(JsonPatch jsonPatch, Customer customer) throws JsonPatchException, JsonProcessingException {
       //JsonPatch patched = jsonPatch.apply(objectMapper.convertValue(customer, JsonPatch.class));
       // JsonPatch origal = objectMapper.convertValue(customer, JsonPatch.class);

        //System.out.println(origal);
       // JsonNode patchNode = objectMapper.readTree(c);
        JsonNode df = jsonPatch.apply(objectMapper.convertValue(customer, JsonNode.class));

        return objectMapper.treeToValue(df,Customer.class);
    }

}







