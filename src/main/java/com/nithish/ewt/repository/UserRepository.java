package com.nithish.ewt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nithish.ewt.dto.UserProjection;
import com.nithish.ewt.entity.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer> {

	@Query(value = "Select * from EARN_WHILE_TRAVEL.user_info", nativeQuery = true)
	List<UserProjection> getAllUsers();

	boolean existsByUserGmail(String userGmail);

	boolean existsByUserRegisterNbr(String userRegisterNbr);

	@Modifying
	@Query(value = "Update EARN_WHILE_TRAVEL.user_info   set user_Gmail = :email where user_Id =:userId", nativeQuery = true)
	int updateUserGmailbyUserId(@Param("userId") long userId, @Param("email") String email);

	@Modifying
	int deleteByUserGmail(String userGmail);

	Optional<UserProjection> findByUserId(long long1);

	Optional<UserProjection> findByUserRegisterNbr(String query);

	Optional<UserProjection> findByUserGmail(String query);

	Optional<UserProjection> findByUserName(String query);



}
