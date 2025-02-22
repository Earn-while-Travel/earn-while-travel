package com.nithish.ewt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.dto.UserResponse;
import com.nithish.ewt.entity.UserTable;
import com.nithish.ewt.repository.UserRepository;
import com.nithish.ewt.service.UserService;

@Service
public class UserSericeImpl implements UserService {

	UserRepository repository;

	@Autowired
	UserSericeImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserResponse getAllUsers() {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		List<UserProjection> projections = repository.getAllUsers();
		response.setRegResponse(projections);

		return response;
	}

	@Override
	@Transactional
	public UserTable saveUser(UserDto dto) {

		UserTable user = new UserTable();
		user.setUserName(dto.getUserName());
		user.setUserGmail(dto.getUserGmail());
		user.setUserRegisterNbr(dto.getUserRegisterNbr());

		return repository.save(user);
	}
}
