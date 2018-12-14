package com.halisaha.customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halisaha.customer.model.Customers;
import com.halisaha.customer.service.CustomersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {


    public Customers customer;

    @Autowired
    public CustomersService customersService;


    @Before
    public void setUp(){
        customer = new Customers("Bozdogan", "Turkusu", "recebim@hotmail.com","12312312312","3123123123",0);
        customersService.addUser(customer);

    }

    @Test
    public void testCustomerService() {

        Customers customer_test = customersService.getUser(customer.getId());
        assert("Bozdogan".matches(customer_test.getName()) ) ;

    }




}