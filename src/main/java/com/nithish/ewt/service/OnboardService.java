package com.nithish.ewt.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.nithish.ewt.dto.UserLoginDto;

public interface OnboardService {

	Optional<UserDetails> userLogin(UserLoginDto userCredentials);

}
