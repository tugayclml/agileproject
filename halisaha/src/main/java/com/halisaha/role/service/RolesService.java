package com.halisaha.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import com.halisaha.role.model.Roles;
import com.halisaha.role.repository.RolesRepository;

@Service
public class RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	public List<Roles> getAllRoles(){
		List<Roles> roles = new ArrayList<>();
		rolesRepository.findAll().forEach(roles::add);
		return roles;
	}
	
	public void addRoles(Roles role) {
		rolesRepository.save(role);
	}

}
