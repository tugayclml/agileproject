package com.halisaha.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.customer.model.Customers;
import com.halisaha.customer.repository.CustomersRepository;

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customerRepository;
	
	public List<Customers> getAllUsers(){
		List<Customers> users = new ArrayList<>();
		customerRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Customers getUser(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public Customers getUserByEmail(String email){
		return customerRepository.findByEmail(email);
	}
	
	public void addUser(Customers user) {
		customerRepository.save(user);
	}
	
	public void updateUser(int id,Customers user) {
		customerRepository.save(user);
	}
	
	public void deleteUser(int id) {
		customerRepository.deleteById(id);
	}
}
