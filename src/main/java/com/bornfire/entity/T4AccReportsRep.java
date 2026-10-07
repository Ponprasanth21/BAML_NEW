package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface T4AccReportsRep extends JpaRepository<T4AccReport, String>{

	Optional<T4AccReport> findById(String directorId);

	
	@Query(value = "select * from BAML_ACCT_APPL_REJ order by SRL_NO", nativeQuery = true)
	Page<T4AccReport> rejAcctList(Pageable page);


	
}