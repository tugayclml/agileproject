package com.halisaha.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.admin.model.Admin;
import com.halisaha.admin.service.AdminService;
import com.halisaha.role.model.Roles;
import com.halisaha.role.service.RolesService;
import com.halisaha.users.model.Users;
import com.halisaha.users.service.UsersService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/admins",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/admins/{id}",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public Admin getAdmin(@PathVariable int id) {
		return adminService.getAdmin(id);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/admins/addAdmin",method=RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addAdmin(@RequestBody Admin admin,HttpServletResponse res) throws IOException{
		String email = admin.getEmail();
		String password = admin.getPassword();
		BCryptPasswordEncoder pw = new BCryptPasswordEncoder();
		String hashedPassword = pw.encode(password);
		admin.setPassword(hashedPassword);
		
		adminService.addAdmin(admin);
		
		Roles userRole = new Roles(email,"ROLE_ADMIN");
		rolesService.addRoles(userRole);
		
		Users users = new Users(email,hashedPassword,1);
		usersService.addUser(users);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/admins/updateAdmin/{id}",method=RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAdmin(@PathVariable int id,@RequestBody Admin admin) {
		adminService.updateAdmin(id, admin);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/admins/deleteAdmin/{id}",method=RequestMethod.DELETE)
	public void deleteAdmin(@PathVariable int id) {
		adminService.deleteAdmin(id);
	}
	
	
}
