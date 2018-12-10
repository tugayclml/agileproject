package com.halisaha.materials.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="spikes")
@Data
@AllArgsConstructor
public class Spikes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	public Spikes(int spikeSize, int spikeNumber) {
		this.spikeSize = spikeSize;
		this.spikeNumber = spikeNumber;
	}

	private int spikeSize;
	private int spikeNumber;
	
}
