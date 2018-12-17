package com.halisaha.announcement.repository;

import org.springframework.data.repository.CrudRepository;

import com.halisaha.announcement.model.Announcement;

public interface AnnouncementRepository extends CrudRepository<Announcement, Integer>{
	public Announcement findByAnnouncementTitle(String announcementTitle);
}
