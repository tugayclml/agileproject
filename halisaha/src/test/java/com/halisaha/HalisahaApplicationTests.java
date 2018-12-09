package com.halisaha;

import com.sun.xml.internal.ws.api.pipe.ContentType;
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

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HalisahaApplicationTests {

    public Customers customer ;
    @LocalServerPort

    private int port;

	public HttpHeaders headers = new HttpHeaders();

	@Autowired
	private TestRestTemplate restTemplate;

    @Before
    public void setUp(){

        customer = new Customers("Emir", "Ozbir", "emirozbir@hotmail.com","abcder34","89467563",0);

    }


	@Test
	public void getCustomers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + 8080 + "/customers",
				String.class)).contains("[]");
	}

	@Test
	public void setCustomers() throws  Exception{


		HttpEntity<Customers> entity = new HttpEntity<Customers>(customer, headers);

		HttpHeaders headers = new HttpHeaders();


		HttpEntity<Customers> request = new HttpEntity<Customers>(customer, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/customer/addCustomer", request , String.class );

		HttpStatus statusCode = response.getStatusCode();
        Assert.assertTrue(("200".matches(statusCode.toString())));

	}
}
