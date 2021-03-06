package com.halisaha.announcement;

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
import com.halisaha.announcement.model.Announcement;
import com.halisaha.announcement.repository.AnnouncementRepository;

public class AnnouncementServiceTest extends AbstractTest{

	@Autowired
	private AnnouncementRepository repository;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getAllAnnouncements() throws Exception{
		String uri="/announcements";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MvcResult mvcResult = 
				mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------GET status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		Announcement[] announcements = super.mapFromJson(content, Announcement[].class);
		assertTrue(announcements.length>0);
	}
	
	@Test
	public void addAnnouncement() throws Exception{
		String uri="/announcements/addAnnouncement";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Announcement announcement = new Announcement();
		announcement.setAnnouncementTitle("Tatil");
		announcement.setAnnouncementContent("Bilmem ne bilmem ne");
		
		String inputJson = super.mapToJson(announcement);
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
	public void updateAnnouncement() throws Exception{
		List<Announcement> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Announcement id  : " + id);
		
		String uri = "/announcements/updateAnnouncement/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Announcement announcement = new Announcement();
		announcement.setAnnouncementContent("Bilmem ne de bilmem ne 2");
		
		String inputJson = super.mapToJson(announcement);
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
	public void deleteAnnouncement() throws Exception{
		List<Announcement> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		
		int id = list.get(0).getId();
		System.out.println("*-*-*--*-*--* Announcement id : " + id);
		
		String uri="/announcements/deleteAnnouncement/" + id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		System.out.println("---------------DELETE status : "+ status);
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("-------------- Content : " + content);
		assertEquals(content, "");
	}
	
}
