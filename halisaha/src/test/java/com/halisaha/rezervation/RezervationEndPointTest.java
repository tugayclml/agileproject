package com.halisaha.rezervation;

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

import com.halisaha.rezervation.model.Rezervation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RezervationEndPointTest {

    public Rezervation rezervation ;

    @LocalServerPort
    private int port;

    public HttpHeaders headers = new HttpHeaders();


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRezervationSet(){

        rezervation = new Rezervation("09:00","BM Idman Yurdu - F.C Barcelona",160);


        HttpEntity<Rezervation> request = new HttpEntity<Rezervation>(rezervation, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/rezervation/addRezervation", request , String.class );

        HttpStatus statusCode = response.getStatusCode();

        Assert.assertTrue(("200".matches(statusCode.toString())));

    }

    @Test
    public void getAllRezervation() throws JSONException {


        ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/rezervations" , String.class );
        JSONAssert.assertNotEquals( "[]", response.getBody(), false);

    }



}
