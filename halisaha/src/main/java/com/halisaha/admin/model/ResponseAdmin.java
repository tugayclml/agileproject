package com.halisaha.admin.model;

import lombok.Data;

@Data
public class ResponseAdmin {
	
	private String userNameAndSurname;
    private String statusMessage;

    public ResponseAdmin(String userNameAndSurname) {
    	this.userNameAndSurname  = userNameAndSurname;
    	this.statusMessage = "User created successfully";
    }

}
