package com.nithish.ewt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nithish.ewt.config.JwtUtil;
import com.nithish.ewt.dto.UserLoginDto;
import com.nithish.ewt.service.OnboardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/onboard/api")
@Validated
public class LoginController {
	public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	OnboardService service;
	JwtUtil jwtUtil;

	LoginController(OnboardService service, JwtUtil jwtUtil) {
		this.service = service;
		this.jwtUtil = jwtUtil;
	}

	@Operation(summary = "userLogin", description = "This methods help user to login with user credentials")
	@PostMapping(value = "/userLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> userLogin(@RequestBody UserLoginDto userCredentials) {
		Map<String, String> response = new HashMap<>();
		Optional<UserDetails> userDetails = service.userLogin(userCredentials);
		if (userDetails.isPresent()) {
			String token = jwtUtil.generateToken(userCredentials.getUserGmail());
			System.out.println(token);
			response.put("message", "User has been logged in successfully");
			LOGGER.info("User login successful for: {}", userCredentials.getUserGmail()); 
																						
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.put("error", "Invalid credentials");
			LOGGER.warn("Failed login attempt for: {}", userCredentials.getUserGmail());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

}
