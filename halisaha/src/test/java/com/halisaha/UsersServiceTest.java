package com.halisaha;

import com.halisaha.customer.repository.CustomersRepository;
import com.halisaha.users.service.UsersService;
import com.halisaha.users.model.Users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {


    public Users user;

    @Autowired
    public UsersService userService;


    @Before
    public void setUp(){
        user = new Users("nkufc@nku.com", "abcd1234523", 0);
        userService.addUser(user);

    }

    @Test
    public void testCustomerService() {

        Users user_test = userService.getUser(user.getId());
        assert("nkufc@nku.com".matches(user_test.getEmail()) ) ;

    }

}