package com.nithish.ewt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nithish.ewt.dto.UserDto;
import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.entity.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer> {
	
	@Query(value ="Select * from EARN_WHILE_TRAVEL.user_info", nativeQuery = true)
	List<UserProjection> getAllUsers();
	 boolean existsByUserGmail(String userGmail);
	    boolean existsByUserRegisterNbr(String userRegisterNbr);

	//UserTable save(UserDto dto);

}
