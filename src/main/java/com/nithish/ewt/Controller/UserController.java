package com.nithish.ewt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.entity.UserTable;
import com.nithish.ewt.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user/api")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity<UserResponse> getAllUser(){
		UserResponse response = userService.getAllUsers();
		return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUserDetails( @Valid @RequestBody UserDto userDto){
		UserTable user = userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully with ID: ");
		
	}
}
