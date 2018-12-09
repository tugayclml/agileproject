package com.halisaha.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.role.model.Roles;
import com.halisaha.role.service.RolesService;
import com.halisaha.staff.model.Staff;
import com.halisaha.staff.service.StaffService;
import com.halisaha.users.model.Users;
import com.halisaha.users.service.UsersService;


@RestController
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@Autowired 
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService; 
	
	@CrossOrigin(origins="http://192.168.2.132:8080")
	@RequestMapping("/staffs")
	public List<Staff> getAllStaffs(){
		return staffService.getAllStaffs();
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping("/staffs/{id}")
	public Staff getStaff(@PathVariable int id) {
		return staffService.getStaff(id);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.POST,value="/staffs/addStaff")
	public void addStaff(@RequestBody Staff staff) {
		int id = staff.getId();
		String email = staff.getEmail();
		String password = staff.getPassword();
		staff.setPassword(password);
		staffService.addStaff(staff);
		
		Roles role = new Roles(email,"ROLE_STAFF");
		rolesService.addRoles(role);
		
		Users user = new Users(id,email,password,1);
		usersService.addUser(user);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.PUT,value="/staffs/updateStaff/{id}")
	public void updateStaff(@RequestBody Staff staff,@PathVariable int id) {
		staffService.updateStaff(staff, id);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.DELETE,value="/staffs/deleteStaff/{id}")
	public void deleteStaff(@PathVariable int id) {
		staffService.deleteStaff(id);
	}
}
