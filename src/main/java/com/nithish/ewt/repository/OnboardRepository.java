package com.nithish.ewt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nithish.ewt.dto.UserLoginProjection;
import com.nithish.ewt.entity.UserTable;

@Repository
public interface OnboardRepository extends JpaRepository<UserTable, String> {
	
	//@Query(value ="Select ")
	Optional<UserLoginProjection> findByUserGmail(String userGmail);

}
