package com.halisaha.materials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.materials.model.SportVest;
import com.halisaha.materials.service.SportVestsService;

@RestController
public class SportsVestController {

	@Autowired
	private SportVestsService sportVestsService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/sportsvests",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<SportVest> getSportsVests(){
		return sportVestsService.getAllSportsVest();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST,value="/sportsvest/addSportsVest",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	private void addSportsVest(@RequestBody SportVest sportVest) {
		sportVestsService.addSportsVest(sportVest);
	}
}
