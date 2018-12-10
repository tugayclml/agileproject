package com.halisaha.rezervation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="rezervations")
@Data
@AllArgsConstructor
public class Rezervation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	public Rezervation(String rezervatedHour, String rezervatedName, int rezervatedPrice) {
		this.rezervatedHour = rezervatedHour;
		this.rezervatedName = rezervatedName;
		this.rezervatedPrice = rezervatedPrice;
	}

	private String rezervatedHour;
	private String rezervatedName;
	private int rezervatedPrice;
	
	

}
