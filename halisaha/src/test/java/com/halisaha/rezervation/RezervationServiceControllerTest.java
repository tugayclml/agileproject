package com.halisaha.rezervation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.halisaha.Abstract.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import com.halisaha.rezervation.model.Rezervation;
import com.halisaha.rezervation.repository.RezervationsRepository;

public class RezervationServiceControllerTest extends AbstractTest{
	
	@Autowired
	private RezervationsRepository repository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllRezervation() throws Exception{
		String uri="/rezervations";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
 		Rezervation[] rezervation = super.mapFromJson(content, Rezervation[].class);
		assertTrue(rezervation.length>0);
	}
	
	@Test
	public void addRezervation() throws Exception{
		String uri="/rezervation/addRezervation";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Rezervation rezervation = new Rezervation();
		rezervation.setRezervatedHour("15:00");
		rezervation.setRezervatedName("Sayid");
		rezervation.setRezervatedSpikes(5);
		rezervation.setRezervatedSportVests(15);
		rezervation.setRezervatedPrice(150);
		
		String inputJson = super.mapToJson(rezervation);
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
	public void updateRezervation() throws Exception{
		
		List<Rezervation> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* id : " + id);
		
		String uri = "/rezervation/updateRezervation/"+id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Rezervation rezervation = new Rezervation();
		rezervation.setRezervatedName("Muhammed Seyitttt");
		
		String inputJson = super.mapToJson(rezervation);
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
	public void deleteRezervation() throws Exception{
		
		List<Rezervation> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* id : " + id);
		
		String uri="/rezervation/deleteRezervation/"+id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------DELETE status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
}
