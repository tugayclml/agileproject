package com.halisaha.filter;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AccountCredentials {

	private String username;
	private String password;
	private Collection<GrantedAuthority> roles;
	
}
