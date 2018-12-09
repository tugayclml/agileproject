package com.halisaha;

import com.halisaha.customer.service.CustomersService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import com.halisaha.customer.model.Customers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerEndPointTest {

    public Customers customer ;
    public CustomersService customersService;
	int customerId ;


    @LocalServerPort

    private int port;

	public HttpHeaders headers = new HttpHeaders();

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void setCustomers() throws  Exception{


		customer = new Customers("Emir", "Ozbir", "recebim@hotmail.com","12312312312","3123123123",0);


		HttpEntity<Customers> entity = new HttpEntity<Customers>(customer, headers);

		HttpHeaders headers = new HttpHeaders();


		HttpEntity<Customers> request = new HttpEntity<Customers>(customer, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/customer/addCustomer", request , String.class );

		HttpStatus statusCode = response.getStatusCode();

        Assert.assertTrue(("200".matches(statusCode.toString())));

        this.customerId = customer.getId();

	}

	@Test
	public void getErrorIndividualCustomer() throws JSONException {

		HttpEntity<Customers> entity = new HttpEntity<Customers>(customer, headers);

		HttpHeaders headers = new HttpHeaders();

		ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/customer/564412312" , String.class );


		JSONAssert.assertEquals( null, response.getBody(), false);

	}



}
