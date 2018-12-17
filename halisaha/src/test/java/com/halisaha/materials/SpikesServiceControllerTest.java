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
import com.halisaha.materials.model.Spikes;

public class SpikesServiceControllerTest extends AbstractTest{
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllSpikes() throws Exception{
		String uri="/spikes";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		Spikes[] spikes = super.mapFromJson(content, Spikes[].class);
		assertTrue(spikes.length>0);
	}
	
	@Test
	public void addSpike() throws Exception{
		String uri="/spike/addSpike";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Spikes spike = new Spikes();
		spike.setSpikeNumber(10);
		spike.setSpikeSize(43);
		
		String inputJson = super.mapToJson(spike);
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.post(uri).headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE).
						content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}
}
