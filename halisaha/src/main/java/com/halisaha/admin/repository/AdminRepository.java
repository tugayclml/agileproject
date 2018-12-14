package com.halisaha.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.halisaha.admin.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	public Admin findByEmail(String email);
}
