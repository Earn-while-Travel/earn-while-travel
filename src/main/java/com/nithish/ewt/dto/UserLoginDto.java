package com.nithish.ewt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
	
	private String userGmail;
	private String userRegisterNbr;
	private String userPassword;

}
