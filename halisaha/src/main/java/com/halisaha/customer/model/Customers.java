package com.halisaha.customer.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
public class Customers implements Serializable{

	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	private String email;


	public Customers(String name, String surname, String email, String password, String phoneNumber, int enabled) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	private String name;
	private String surname;
	private String password;
	private String phoneNumber;
	private int enabled;
	
}
