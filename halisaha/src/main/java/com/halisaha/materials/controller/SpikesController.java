package com.halisaha.materials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/spikes")
	public List<Spikes> getAllSpikes(){
		return spikesService.getAllSpikes();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/spike/addSpike")
	public void addSpike(@RequestBody Spikes spike) {
		spikesService.addSpike(spike);
	}
}
