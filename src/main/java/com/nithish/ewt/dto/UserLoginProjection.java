package com.nithish.ewt.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userGmail","userRegisterNbr","userPassword"})
public interface UserLoginProjection {
	
	String getUserGmail();
	String getUserRegisterNbr();
	String getUserPassword();

}
