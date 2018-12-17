package com.halisaha.materials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.halisaha.Abstract.AbstractTest;
import com.halisaha.materials.model.SportVest;

public class SportVestServiceControllerTest extends AbstractTest{

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllSportVests() throws Exception{
		String uri="/sportsvests";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		SportVest[] sportVest = super.mapFromJson(content, SportVest[].class);
		assertTrue(sportVest.length>0);
	}
	
	@Test
	public void addSportVest() throws Exception{
		String uri="/sportsvest/addSportsVest";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		SportVest sportVest = new SportVest();
		sportVest.setVestColor("green");
		sportVest.setVestNumber(25);
		
		String inputJson = super.mapToJson(sportVest);
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE).
						content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}
}
