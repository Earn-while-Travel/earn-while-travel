package com.nithish.ewt.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userId","userName","userGmail","userRegisterNbr"})
public interface UserProjection {
	int getUserId();
	String getUserName();
	String getUserGmail();
	
	String getUserRegisterNbr();

}
