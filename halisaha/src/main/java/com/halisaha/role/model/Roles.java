package com.halisaha.role.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="userroles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String email;
	private String authority;
	
	public Roles(String email,String authority) {
		this.email = email;
		this.authority = authority;
	}
}
