package com.halisaha.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.halisaha.staff.repository.StaffRepository;
import com.halisaha.staff.model.Staff;

@Service
public class StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	public List<Staff> getAllStaffs(){
		List<Staff> staffs = new ArrayList<>();
		staffRepository.findAll().forEach(staffs::add);
		return staffs;
	}
	
	public Staff getStaff(int id) {
		return staffRepository.findById(id).orElse(null);
	}
	
	public void addStaff(Staff staff) {
		staffRepository.save(staff);
	}
	
	public void updateStaff(Staff staff,int id) {
		staffRepository.save(staff);
	}
	
	public void deleteStaff(int id) {
		staffRepository.deleteById(id);
	}
}
