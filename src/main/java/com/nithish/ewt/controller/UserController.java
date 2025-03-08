package com.nithish.ewt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nithish.ewt.dto.ApiResponse;
import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/api")
@Validated
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value="/getAllUsers",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getAllUser() {
		UserResponse response = userService.getAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary="Create user", description="Create a new user")
	@PostMapping(value="/saveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> saveUserDetails(@Valid @RequestBody UserDto userDto) {
		ApiResponse response = userService.saveUser(userDto); // Ensure this returns a valid ID

	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary="update user",description="Update user email address")
	@PutMapping(value="/updateUserEmail",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateUserEmail(@RequestParam("userId") long userId,
			@RequestParam("userEmail") String email) {
		ApiResponse response = userService.updateUserEmail(userId, email);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@Operation(summary="Delete user",description="Delete user with given userGmail address")
	@DeleteMapping(value="/deleteUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> deleteUser(@RequestParam("userGmail") String userGmail) {
		ApiResponse response = userService.deleteUserProfile(userGmail);
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
	
	@GetMapping(value="/userSearch",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProjection> userInfoSearch(@RequestParam("idOrRegNbrOrEmail") String query){
		UserProjection response = userService.globalSearch(query);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
