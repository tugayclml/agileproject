package com.halisaha.materials.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.materials.model.Spikes;
import com.halisaha.materials.repository.SpikesRepository;

@Service
public class SpikesService {

	@Autowired
	private SpikesRepository spikesRepository;
	
	public List<Spikes> getAllSpikes(){
		List<Spikes> spikes = new ArrayList<>();
		spikesRepository.findAll().forEach(spikes::add);
		return spikes;
	}
	
	public void addSpike(Spikes spike) {
		spikesRepository.save(spike);
	}
}
