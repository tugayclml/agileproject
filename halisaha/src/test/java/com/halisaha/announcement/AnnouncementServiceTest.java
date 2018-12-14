package com.halisaha.announcement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halisaha.announcement.model.Announcement;
import com.halisaha.announcement.service.AnnouncementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnouncementServiceTest {


    public Announcement announcement;

    @Autowired
    public AnnouncementService announcementService;


    @Before
    public void setUp(){
    	announcement = new Announcement("Kar yağışı nedeyile kapalıyız","23/12/2018-25/12/2018 tarihleri arasında kapalıyız.");
    	announcementService.addAnnouncement(announcement);

    }

    @Test
    public void testAnnouncementService() {

        Announcement announcement_test = announcementService.getAnnouncement(announcement.getAnnouncementTitle());
        assert("Kar yağışı nedeyile kapalıyız".matches(announcement_test.getAnnouncementTitle()));

    }
}
