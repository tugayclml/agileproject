package com.halisaha.announcement.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import com.halisaha.announcement.model.Announcement;
import com.halisaha.announcement.repository.AnnouncementRepository;

@Service
public class AnnouncementService {
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	public List<Announcement> getAllAnnouncements(){
		List<Announcement> announcements = new ArrayList<>();
		announcementRepository.findAll().forEach(announcements::add);
		return announcements;
	}
	
	public Announcement getAnnouncement(String announcementTitle) {
		return announcementRepository.findByAnnouncementTitle(announcementTitle);
	}
	
	public void addAnnouncement(Announcement announcement) {
		announcementRepository.save(announcement);
	}
	
	public void updateAnnouncement(Announcement announcement,String announcementTitle) {
		announcementRepository.save(announcement);
	}
	
	public void deleteAnnouncement(String announcementTitle) {
		announcementRepository.deleteByAnnouncementTitle(announcementTitle);
	}

}
