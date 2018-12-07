package com.halisaha.materials.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halisaha.materials.model.SportVest;
import com.halisaha.materials.service.SportVestsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SportsVestController {

	@Autowired
	private SportVestsService sportVestsService;
	
	@RequestMapping("/sportsvests")
	private List<SportVest> getSportsVests(){
		return sportVestsService.getAllSportsVest();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/sportsvest/addSportsVest")
	private void addSportsVest(@RequestBody SportVest sportVest) {
		sportVestsService.addSportsVest(sportVest);
	}
}
