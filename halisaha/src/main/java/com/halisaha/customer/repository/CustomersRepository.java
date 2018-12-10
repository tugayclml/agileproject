package com.halisaha.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.halisaha.customer.model.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Integer>{
    public Customers findByEmail(String email);
}

