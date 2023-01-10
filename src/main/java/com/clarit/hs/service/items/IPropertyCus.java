package com.clarit.hs.service.items;

import java.util.List;

public interface IPropertyCus {

    public List<Customer> getAll(String id);

    public List<Customer> get(String customerName);

    public Customer add(String customerName);
}

