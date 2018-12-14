package com.halisaha.announcement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.announcement.model.Announcement;
import com.halisaha.announcement.service.AnnouncementService;

@RestController
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/announcements",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<Announcement> getAllAnnouncements(){
		return announcementService.getAllAnnouncements();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/announcements/{announcementTitle}",method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private Announcement getAnnouncements(@PathVariable String announcementTitle){
		return announcementService.getAnnouncement(announcementTitle);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/announcements/addAnnouncement",method=RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private void addAnnouncement(@RequestBody Announcement announcement) {
		announcementService.addAnnouncement(announcement);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/announcements/updateAnnouncement/{announcementTitle}",method=RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private void updateAnnouncement(@RequestBody Announcement announcement,@PathVariable String announcementTitle) {
		announcementService.updateAnnouncement(announcement, announcementTitle);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/announcements/deleteAnnouncement/{announcementTitle}",method=RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private void deleteAnnouncement(@PathVariable String announcementTitle) {
		announcementService.deleteAnnouncement(announcementTitle);
	}
}
