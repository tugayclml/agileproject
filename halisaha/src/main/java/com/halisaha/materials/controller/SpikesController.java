package com.halisaha.materials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.halisaha.materials.model.Spikes;
import com.halisaha.materials.service.SpikesService;

@RestController
public class SpikesController {
	
	@Autowired
	private SpikesService spikesService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/spikes",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Spikes> getAllSpikes(){
		return spikesService.getAllSpikes();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST,value="/spike/addSpike",
			produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSpike(@RequestBody Spikes spike) {
		spikesService.addSpike(spike);
	}
}
