package com.halisaha.rezervation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halisaha.rezervation.repository.RezervationsRepository;
import com.halisaha.rezervation.model.Rezervation;

@Service
public class RezervationsService {
	
	@Autowired
	private RezervationsRepository rezervationsRepository;
	
	public List<Rezervation> getAllRezervations(){
		List<Rezervation> rezervations = new ArrayList<>();
		rezervationsRepository.findAll().forEach(rezervations::add);
		return rezervations;
	}
	
	public Rezervation getRezervation(int id) {
		return rezervationsRepository.findById(id).orElse(null);
	}
	
	public void addReservation(Rezervation rezervation) {
		rezervationsRepository.save(rezervation);
	}
	
	public void updateRezervation(int id,Rezervation rezervation) {
		rezervationsRepository.save(rezervation);
	}
	
	public void deleteRezervation(int id) {
		rezervationsRepository.deleteById(id);
	}
}
