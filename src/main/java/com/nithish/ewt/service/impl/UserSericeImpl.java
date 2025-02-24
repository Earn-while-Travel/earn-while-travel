package com.nithish.ewt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.entity.UserTable;
import com.nithish.ewt.exception.EWTException;
import com.nithish.ewt.repository.UserRepository;
import com.nithish.ewt.service.UserService;
import com.nithish.ewt.utils.Constants;

@Service
public class UserSericeImpl implements UserService {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserSericeImpl.class);
	UserRepository repository;

	@Autowired
	UserSericeImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserResponse getAllUsers() {
		UserResponse response = new UserResponse();
		List<UserProjection> projections = repository.getAllUsers();
		response.setRegResponse(projections);

		return response;
	}

	@Override
	@Transactional
	public UserTable saveUser(UserDto dto) {

		if (repository.existsByUserGmail(dto.getUserGmail())) {
			LOGGER.error(Constants.EMAIL_ALREADY_EXISTS, dto.getUserGmail());
			throw new EWTException(Constants.EMAIL_ALREADY_EXISTS + dto.getUserGmail());
		}
		if (repository.existsByUserRegisterNbr(dto.getUserRegisterNbr())) {
			LOGGER.info(Constants.REGISTER_NUMBER_ALREADY_EXISTS , dto.getUserRegisterNbr());
			throw new EWTException(Constants.REGISTER_NUMBER_ALREADY_EXISTS + dto.getUserRegisterNbr());
		}
		LOGGER.info("Creating user with email: {} ",dto.getUserGmail());
		UserTable user = new UserTable();
		user.setUserName(dto.getUserName());
		user.setUserGmail(dto.getUserGmail());
		user.setUserRegisterNbr(dto.getUserRegisterNbr());

		return repository.save(user);
	}

	@Override
	@Transactional
	public int updateUserEmail(long userId, String email) throws EWTException{
		boolean existCheck = repository.existsByUserGmail(email);
		if (existCheck) {
			throw new EWTException(Constants.EMAIL_ALREADY_EXISTS + email);
		}
		int updatedRows = repository.updateUserGmailbyUserId(userId, email);
		if (updatedRows == 0) {
			throw new EWTException("User Not updated for given email " + email);
		}
		return updatedRows;

	}

	@Override
	@Transactional
	public int deleteUserProfile(String userGmail) {
		int deletedRows = repository.deleteByUserGmail(userGmail);
		if (deletedRows == 0) {
			throw new EWTException("No user present with given user email: " + userGmail);
		}
		return deletedRows;
	}
}
