package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserProfileModRep extends CrudRepository<UserProfileModEn, String> {

	public Optional<UserProfileModEn> findByUserid(String userid);

}
