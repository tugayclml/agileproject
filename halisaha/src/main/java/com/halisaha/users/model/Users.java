package com.halisaha.users.model;

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
@Table(name="users")
@Data
@AllArgsConstructor
public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;



	private String email;
	private String password;
	private int enabled;

	public Users(String email, String password, int enabled) {
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

}
