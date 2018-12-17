package com.halisaha.customer;

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
import com.halisaha.customer.model.Customers;
import com.halisaha.customer.repository.CustomersRepository;

public class CustomerServiceControllerTest extends AbstractTest{
	
	@Autowired
	private CustomersRepository repository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllCustomers() throws Exception{
		String uri="/customers";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		Customers[] customers = super.mapFromJson(content, Customers[].class);
		assertTrue(customers.length>0);
	}
	
	@Test
	public void addCustomer() throws Exception{
		String uri="/customer/addCustomer";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Customers customer = new Customers();
		customer.setName("İlker");
		customer.setSurname("Yasin Özdemir");
		customer.setEmail("ilker.yasin@gmail.com");
		customer.setPassword("ilker");
		customer.setPhoneNumber("05369874125");
		customer.setEnabled(1);
		
		String inputJson = super.mapToJson(customer);
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
	public void updateCustomer() throws Exception{
		List<Customers> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* id : " + id);
		
		String uri = "/customer/updateCustomer/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Customers customer = new Customers();
		customer.setName("İLKER");
		
		String inputJson = super.mapToJson(customer);
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
	public void deleteCustomer() throws Exception{
		List<Customers> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* id : " + id);
		
		String uri="/customer/deleteCustomer/" + id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------DELETE status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
}
