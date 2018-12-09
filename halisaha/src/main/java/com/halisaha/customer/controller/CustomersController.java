package com.halisaha.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.customer.model.Customers;
import com.halisaha.customer.service.CustomersService;
import com.halisaha.role.model.Roles;
import com.halisaha.role.service.RolesService;
import com.halisaha.users.model.Users;
import com.halisaha.users.service.UsersService;

@RestController
public class CustomersController {
	
	@Autowired
	private CustomersService customerService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService; 
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping("/customers")
	public List<Customers> getAllUsers(){
		return customerService.getAllUsers();
	}

	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping("/userRoles")
	public List<Roles> getAllRoles(){
		return rolesService.getAllRoles();
	}

	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping("/customer/{id}")
	public Customers getUser(@PathVariable int id) {
		return customerService.getUser(id);
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.POST,value="/customer/addCustomer")
	public void addUser(@RequestBody Customers customer) {
		int id = customer.getId();
		String email = customer.getEmail();
		String password = customer.getPassword();
		customer.setPassword(password);
		customerService.addUser(customer);
		
		Roles userRole = new Roles(email,"ROLE_USER");
		rolesService.addRoles(userRole);
		
		Users users = new Users(id,email,password,1);
		usersService.addUser(users);
		
		
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.PUT,value="/customer/updateCustomer/{id}")
	public void updateUser(@RequestBody Customers customer, @PathVariable int id) {
		customerService.updateUser(id, customer);
 
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.DELETE,value="/customer/deleteCustomer/{id}")
	public void deleteUser(@PathVariable int id) {
		customerService.deleteUser(id);
	}
}
