package com.halisaha.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halisaha.admin.model.Admin;
import com.halisaha.admin.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {


    public Admin admin;

    @Autowired
    public AdminService adminService;


    @Before
    public void setUp(){
        admin = new Admin("Bozdogan", "Turkusu", "recebim@hotmail.com","12312312312","3123123123",1);
        adminService.addAdmin(admin);

    }

    @Test
    public void testAdminService() {

        Admin admin_test = adminService.getAdmin(admin.getId());
        assert("Bozdogan".matches(admin_test.getName()) ) ;

    }
}
