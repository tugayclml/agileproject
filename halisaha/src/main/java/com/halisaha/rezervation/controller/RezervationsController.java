package com.halisaha.rezervation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/rezervations",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Rezervation> getAllRezervations(){
		return rezervationsService.getAllRezervations();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/rezervation/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rezervation getRezervation(@PathVariable int id) {
		return rezervationsService.getRezervation(id);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST,value="/rezervation/addRezervation/{rezervatedName}",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addRezervation(@RequestBody Rezervation rezervation,@PathVariable String rezervatedName) {
		rezervation.setRezervatedName(rezervatedName);
		rezervationsService.addReservation(rezervation);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.PUT,value="/rezervation/updateRezervation/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRezervation(@RequestBody Rezervation rezervation,@PathVariable int id) {
		rezervationsService.updateRezervation(id, rezervation);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.DELETE,value="/rezervation/deleteRezervation/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRezervation(@PathVariable int id) {
		rezervationsService.deleteRezervation(id);
	}

}
