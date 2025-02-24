package com.nithish.ewt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nithish.ewt.dto.UserDto;
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
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	@Operation(summary="Create user",description="Create a new user")
	@PostMapping(value="/saveUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUserDetails(@Valid @RequestBody UserDto userDto) {
		userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully with ID: ");

	}
	@Operation(summary="update user",description="Update user email address")
	@PutMapping(value="/updateUserEmail",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserEmail(@RequestParam("userId") long userId,
			@RequestParam("userEmail") String email) {
		userService.updateUserEmail(userId, email);
		return ResponseEntity.ok("User Updated successfullly for given userId: " + userId);
	}
	@Operation(summary="Delete user",description="Delete user with given userGmail address")
	@DeleteMapping(value="/deleteUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@RequestParam("userGmail") String userGmail) {
		userService.deleteUserProfile(userGmail);
		return ResponseEntity.ok("User Deleted Successfully" + userGmail);

	}
}
