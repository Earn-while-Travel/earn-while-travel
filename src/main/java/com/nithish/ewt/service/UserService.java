package com.nithish.ewt.service;

import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.entity.UserTable;

public  interface UserService {

	UserResponse getAllUsers();
	UserTable saveUser(UserDto dto);
	
	

}
