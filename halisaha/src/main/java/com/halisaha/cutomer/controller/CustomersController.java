package com.halisaha.cutomer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.customer.model.Customers;
import com.halisaha.cutomer.service.CustomersService;
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
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping("/customers")
	public List<Customers> getAllUsers(){
		return cutomerService.getAllUsers();
	}

	@RequestMapping("/userRoles")
	public List<Roles> getAllRoles(){
		return rolesService.getAllRoles();
	}

	@RequestMapping("/customer/{id}")
	public Customers getUser(@PathVariable int id) {
		return cutomerService.getUser(id);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.POST,value="/customer/addCustomer")
	public void addUser(@RequestBody Customers customer) {
		int id = customer.getId();
		String email = customer.getEmail();
		String password = customer.getPassword();
		BCryptPasswordEncoder pw = new BCryptPasswordEncoder();
		String hashedPassword = pw.encode(password);
		customer.setPassword(hashedPassword);
		cutomerService.addUser(customer);
		
		Roles userRole = new Roles(email,"ROLE_USER");
		rolesService.addRoles(userRole);
		
		Users users = new Users(id,email,hashedPassword,1);
		usersService.addUser(users);
		
		
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.PUT,value="/customer/updateCustomer/{id}")
	public void updateUser(@RequestBody Customers customer, @PathVariable int id) {
		cutomerService.updateUser(id, customer);
 
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.DELETE,value="/customer/deleteCustomer/{id}")
	public void deleteUser(@PathVariable int id) {
		cutomerService.deleteUser(id);
	}
}
