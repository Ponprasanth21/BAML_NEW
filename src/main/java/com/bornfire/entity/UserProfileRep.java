package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserProfileRep extends CrudRepository<UserProfile, String> {

	public Optional<UserProfile> findByusername(String userName);

	 @Query(value = "SELECT COUNT(*) " +
             "FROM \"AML\".\"BAML_USER_PROFILE_TABLE\" " +
             "WHERE \"DEL_FLG\" = 'N' " +
             "AND \"USER_ID\" = ?1",
     nativeQuery = true)
	String getusercount(String custId);
}
