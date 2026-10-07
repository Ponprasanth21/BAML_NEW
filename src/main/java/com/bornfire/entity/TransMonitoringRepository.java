package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransMonitoringRepository extends JpaRepository<Transaction, String> {

	Optional<Transaction> findById(String directorId);

	@Query(value = "select * from trans_details where TRAN_AMT between '?1' and '?2'", nativeQuery = true)
	List<Transaction> findByAll(String vmin, String vmax);

	@Query(value = "select * from trans_details where TRAN_AMT between ?1 and ?2", nativeQuery = true)
	Page<Transaction> findAllCustomList(Pageable page, BigDecimal value_min, BigDecimal value_max);
	
	@Query(value = "select * from trans_details where TRAN_DATE=?1", nativeQuery = true)
	Page<Transaction> findByAll(Pageable page, Date tran_date);

	
	

	
	
	@Query(value = "select count(*) from trans_details", nativeQuery = true)
	long findtrancount();
	
	
	@Query(value = "select count(*) from trans_details where TRAN_STATUS='success'", nativeQuery = true)
	long findtransucescount();
	
	@Query(value = "select count(*) from trans_details where TRAN_STATUS='Failed'", nativeQuery = true)
	long findtranfailurecount();


}