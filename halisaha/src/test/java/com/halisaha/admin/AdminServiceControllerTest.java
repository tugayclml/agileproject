package com.halisaha.admin;

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
import com.halisaha.admin.model.Admin;
import com.halisaha.admin.repository.AdminRepository;

public class AdminServiceControllerTest extends AbstractTest{

	@Autowired
	private AdminRepository repository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllAdmins() throws Exception{
		String uri="/admins";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		Admin[] admins = super.mapFromJson(content, Admin[].class);
		assertTrue(admins.length>0);
	}
	
	@Test
	public void addAdmin() throws Exception{
		String uri="/admins/addAdmin";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Admin admin = new Admin();
		admin.setName("Ferdi");
		admin.setSurname("Tayfur");
		admin.setEmail("ferdibabaaaaa@hotmail.com");
		admin.setPassword("ferdi");
		admin.setEnabled(1);
		admin.setPhoneNumber("05645464643");
		
		String inputJson = super.mapToJson(admin);
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
	public void updateAdmin() throws Exception{
		List<Admin> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Admin id  : " + id);
		
		String uri = "/admins/updateAdmin/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Admin admin = new Admin();
		admin.setName("REZZAK");
		
		String inputJson = super.mapToJson(admin);
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
	public void deleteAdmin() throws Exception{
		List<Admin> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Admin id : " + id);
		
		String uri="/admins/deleteAdmin/" + id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------DELETE status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
}
