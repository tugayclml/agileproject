package com.halisaha.staff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.halisaha.Abstract.AbstractTest;
import com.halisaha.staff.model.Staff;
import com.halisaha.staff.repository.StaffRepository;

public class StaffServiceControllerTest extends AbstractTest{

	@Autowired
	private StaffRepository repository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllStaffs() throws Exception{
		String uri="/staffs";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		Staff[] staffs = super.mapFromJson(content, Staff[].class);
		assertTrue(staffs.length>0);
	}
	
	@Test
	public void addStaff() throws Exception{
		String uri="/staffs/addStaff";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Staff staff = new Staff();
		staff.setName("Ferdi");
		staff.setSurname("Tayfur");
		staff.setEmail("ferdibabaaaaa@hotmail.com");
		staff.setPassword("ferdi");
		staff.setEnabled(1);
		staff.setPhoneNumber("05645464643");
		
		String inputJson = super.mapToJson(staff);
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE).
						content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------POST status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
	@Test
	public void updateStaff() throws Exception{
		List<Staff> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Admin id  : " + id);
		
		String uri = "/staffs/updateStaff/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Staff staff = new Staff();
		staff.setEmail("Broooo@gmail.com");
		
		
		String inputJson = super.mapToJson(staff);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).headers(headers)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		System.out.println("---------------UPDATE status : "+ status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
	@Test
	public void deleteStaff() throws Exception{
		List<Staff> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Admin id : " + id);
		
		String uri="/staffs/deleteStaff/" + id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------DELETE status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
}
