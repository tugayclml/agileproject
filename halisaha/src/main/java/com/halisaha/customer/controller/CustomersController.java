package com.halisaha.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private CustomersService cutomerService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService; 
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/customers",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Customers> getAllUsers(){
		return cutomerService.getAllUsers();
	}

	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/userRoles",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Roles> getAllRoles(){
		return rolesService.getAllRoles();
	}

	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/customer/{id}",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customers getUser(@PathVariable int id) {
		return cutomerService.getUser(id);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST,value="/customer/addCustomer",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public void addUser(@RequestBody Customers customer) {
		String email = customer.getEmail();
		String password = customer.getPassword();
		String name = customer.getName();
		String surname = customer.getSurname();
		String phoneNumber = customer.getPhoneNumber();
		BCryptPasswordEncoder pw = new BCryptPasswordEncoder();
		String hashedPassword = pw.encode(password);
		customer.setPassword(hashedPassword);
		
		Customers c = new Customers(name,surname,email,hashedPassword,phoneNumber,1);
		cutomerService.addUser(c);
		
		Roles userRole = new Roles(email,"ROLE_USER");
		rolesService.addRoles(userRole);
		
		Users users = new Users(email,hashedPassword,1);
		usersService.addUser(users);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.PUT,value="/customer/updateCustomer/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody Customers customer, @PathVariable int id) {
		cutomerService.updateUser(id, customer);
 
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.DELETE,value="/customer/deleteCustomer/{id}")
	public void deleteUser(@PathVariable int id) {
		cutomerService.deleteUser(id);
	}
}
