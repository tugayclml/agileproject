package com.halisaha.rezervation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halisaha.rezervation.model.Rezervation;
import com.halisaha.rezervation.service.RezervationsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervationServiceTest {


    public Rezervation rezervation;

    @Autowired
    public RezervationsService rezervationService;


    @Before
    public void setUp(){
    	rezervation = new Rezervation("09:00","BM Idman Yurdu - F.C Barcelona",160);
    	rezervationService.addReservation(rezervation);

    }

    @Test
    public void testRezervationService() {

        Rezervation rezervation_test = rezervationService.getRezervation(rezervation.getId());
        assert("BM Idman Yurdu - F.C Barcelona".matches(rezervation_test.getRezervatedName()) ) ;

    }




}
