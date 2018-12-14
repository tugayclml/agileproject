package com.halisaha.users.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.users.model.Users;
import com.halisaha.users.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getAllUsers(){
		List<Users> users = new ArrayList<>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public void addUser(Users user) {
		usersRepository.save(user);
	}
	
	public Users getUser(int id) {
		return usersRepository.findById(id).orElse(null);
	}
}
