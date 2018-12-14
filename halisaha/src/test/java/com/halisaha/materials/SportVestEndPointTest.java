package com.halisaha.materials;

import org.json.JSONException;
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

import com.halisaha.materials.model.SportVest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SportVestEndPointTest{

    public SportVest sportVest;

    @LocalServerPort
    private int port;

    public HttpHeaders headers = new HttpHeaders();


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void setSportVest(){

    	sportVest = new SportVest("red",25);


        HttpEntity<SportVest> request = new HttpEntity<SportVest>(sportVest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/sportsvest/addSportsVest", request , String.class );

        HttpStatus statusCode = response.getStatusCode();

        Assert.assertTrue(("200".matches(statusCode.toString())));

    }

    @Test
    public void getAllSportVests() throws JSONException {


        ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/sportsvests" , String.class );
        JSONAssert.assertNotEquals( "[]", response.getBody(), false);

    }



}
