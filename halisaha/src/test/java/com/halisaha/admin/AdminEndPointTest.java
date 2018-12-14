package com.halisaha.admin;

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

import com.halisaha.admin.model.Admin;
import com.halisaha.admin.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminEndPointTest {

    public Admin admin ;
	int adminId ;


    @LocalServerPort

    private int port;

	public HttpHeaders headers = new HttpHeaders();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	public AdminService adminService;

	@Test
	public void setAdmin() throws  Exception{


		admin = new Admin("Emir", "Ozbir", "recebim@hotmail.com","12312312312","3123123123",1);


		HttpEntity<Admin> entity = new HttpEntity<Admin>(admin, headers);

		HttpHeaders headers = new HttpHeaders();


		HttpEntity<Admin> request = new HttpEntity<Admin>(admin, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/admins/addAdmin", request , String.class );

		HttpStatus statusCode = response.getStatusCode();

        Assert.assertTrue(("200".matches(statusCode.toString())));

	}

	@Test
	public void getErrorIndividualAdmin() throws JSONException {

		ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/customer/asdasd" , String.class );


		JSONAssert.assertEquals( null, response.getBody(), false);

	}


	@Test
	public void getIndividualAdmin() throws JSONException{
		HttpEntity<Admin> entity = new HttpEntity<Admin>(admin, headers);
		HttpHeaders headers = new HttpHeaders();

		Admin adminTemp = adminService.getUserByEmail("recebim@hotmail.com");

		ResponseEntity<JSONObject> response = restTemplate.getForEntity( "http://localhost:8080/customer/"+adminTemp.getId() , JSONObject.class );

		assert((response.getStatusCode().toString().matches("200") ));


	}



}
