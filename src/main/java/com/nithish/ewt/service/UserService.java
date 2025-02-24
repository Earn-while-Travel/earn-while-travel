package com.nithish.ewt.service;

import com.nithish.ewt.dto.ApiResponse;
import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.entity.UserTable;

public  interface UserService {

	UserResponse getAllUsers();
	ApiResponse saveUser(UserDto dto);
	ApiResponse updateUserEmail(long userId, String email);
	ApiResponse deleteUserProfile(String userGmail);
	UserProjection globalSearch(String query);
	
	

}
