package com.halisaha.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.admin.model.Admin;
import com.halisaha.admin.repository.AdminRepository;

import java.util.List;
import java.util.ArrayList;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public List<Admin> getAllAdmins(){
		List<Admin> admins = new ArrayList<>();
		adminRepository.findAll().forEach(admins::add);
		return admins;
	}
	
	public Admin getAdmin(int id) {
		return adminRepository.findById(id).orElse(null);
	}
	
	public Admin getUserByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	public void addAdmin(Admin admin){
		adminRepository.save(admin);
	}
	
	public void updateAdmin(int id,Admin admin) {
		adminRepository.save(admin);
	}
	
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}
}
