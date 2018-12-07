package com.halisaha.rezervation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.rezervation.model.Rezervation;
import com.halisaha.rezervation.service.RezervationsService;

@RestController
public class RezervationsController {
	
	@Autowired
	private RezervationsService rezervationsService;
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping("/rezervations")
	public List<Rezervation> getAllRezervations(){
		return rezervationsService.getAllRezervations();
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping("/rezervation/{id}")
	public Rezervation getRezervation(@PathVariable int id) {
		return rezervationsService.getRezervation(id);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.POST,value="/rezervation/addRezervation")
	public void addRezervation(@RequestBody Rezervation rezervation) {
		rezervationsService.addReservation(rezervation);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.PUT,value="/rezervation/updateRezervation/{id}")
	public void updateRezervation(@RequestBody Rezervation rezervation,@PathVariable int id) {
		rezervationsService.updateRezervation(id, rezervation);
	}
	
	@CrossOrigin(origins="http://192.168.2.132:4200")
	@RequestMapping(method=RequestMethod.DELETE,value="/rezervation/deleteRezervation/{id}")
	public void deleteRezervation(@PathVariable int id) {
		rezervationsService.deleteRezervation(id);
	}

}
