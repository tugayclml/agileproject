package com.halisaha.customer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phoneNumber;
	private int enabled;
	
	public Customers(String name,String surname,String email,String password,String phoneNumber,int enabled) {
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.password=password;
		this.phoneNumber=phoneNumber;
		this.enabled = enabled;
	}
}
