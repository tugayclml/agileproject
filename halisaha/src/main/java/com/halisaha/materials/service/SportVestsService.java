package com.halisaha.materials.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.materials.model.SportVest;
import com.halisaha.materials.repository.SportVestsRepository;

@Service
public class SportVestsService {

	@Autowired
	private SportVestsRepository sportVestsRepository;
	
	public List<SportVest> getAllSportsVest(){
		List<SportVest> sportVest = new ArrayList<>();
		sportVestsRepository.findAll().forEach(sportVest::add);
		return sportVest;
	}
	
	public void addSportsVest(SportVest sportVest) {
		sportVestsRepository.save(sportVest);
	}
}
