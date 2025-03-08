package com.nithish.ewt.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nithish.ewt.dto.UserLoginDto;
import com.nithish.ewt.dto.UserLoginProjection;
import com.nithish.ewt.repository.OnboardRepository;
import com.nithish.ewt.service.OnboardService;

@Service
public class OnboardServiceImpl implements OnboardService {
	public static final Logger LOGGER = LoggerFactory.getLogger(OnboardServiceImpl.class);

	OnboardRepository onboardRepository;
	private final PasswordEncoder passwordEncoder;

	public OnboardServiceImpl(OnboardRepository onboardRepository) {
		this.onboardRepository = onboardRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public boolean userLogin(UserLoginDto userCredentials) {
		Optional<UserLoginProjection> optionalUser = onboardRepository.findByUserGmail(userCredentials.getUserGmail());

		if (optionalUser.isEmpty()) {
			LOGGER.warn("Login failed: User not found for email {}", userCredentials.getUserGmail());
			return false;
		}

		UserLoginProjection user = optionalUser.get();

		if (!passwordEncoder.matches(userCredentials.getUserPassword(), user.getUserPassword())) {
			LOGGER.warn("Login failed: Incorrect password for email {}", userCredentials.getUserGmail());
			return false;
		}

		if (!Objects.equals(user.getUserRegisterNbr(), userCredentials.getUserRegisterNbr())) {
			LOGGER.warn("Login failed: Incorrect registration number for email {}", userCredentials.getUserGmail());
			return false;
		}

		LOGGER.info("User login successful: {}", userCredentials.getUserGmail());
		return true;
	}
}
