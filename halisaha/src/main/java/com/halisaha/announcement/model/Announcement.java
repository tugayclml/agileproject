package com.halisaha.announcement.model;

import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="announcements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String announcementTitle;
	private String announcementContent;
	
	public Announcement(String announcementTitle,String announcementContent) {
		this.announcementTitle = announcementTitle;
		this.announcementContent = announcementContent;
	}
}
