package com.halisaha.announcement;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.halisaha.announcement.model.Announcement;
import com.halisaha.announcement.service.AnnouncementService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AnnouncementEndPointTest {

    public Announcement announcement ;
	int announcementId ;


    @LocalServerPort

    private int port;

	public HttpHeaders headers = new HttpHeaders();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	public AnnouncementService announcementService;

	@Test
	public void setAnnouncement() throws  Exception{


		announcement = new Announcement("Kar yağışı nedeyile kapalıyız","23/12/2018-25/12/2018 tarihleri arasında kapalıyız.");


		HttpEntity<Announcement> entity = new HttpEntity<Announcement>(announcement, headers);

		HttpHeaders headers = new HttpHeaders();


		HttpEntity<Announcement> request = new HttpEntity<Announcement>(announcement, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/announcements/addAnnouncement", request , String.class );

		HttpStatus statusCode = response.getStatusCode();

        Assert.assertTrue(("200".matches(statusCode.toString())));

	}

	@Test
	public void getErrorIndividualAnnouncement() throws JSONException {

		ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/announcements/addAnnouncement" , String.class );


		JSONAssert.assertEquals( null, response.getBody(), false);

	}


	@Test
	public void getIndividualAnnouncement() throws JSONException{
		HttpEntity<Announcement> entity = new HttpEntity<Announcement>(announcement, headers);
		HttpHeaders headers = new HttpHeaders();

		Announcement announcementTemp = announcementService.getAnnouncement("Kar yağışı nedeyile kapalıyız");

		ResponseEntity<JSONObject> response = restTemplate.getForEntity( "http://localhost:8080/announcements/"+announcementTemp.getAnnouncementTitle() , JSONObject.class );

		assert((response.getStatusCode().toString().matches("200") ));


	}



}