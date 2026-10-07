package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAMLCustomerChecksRepo extends JpaRepository<BAMLCustomerChecks, BigDecimal>{
	
	 @Query(value = "select * from BAML_ON_BOARD_CHECKS order by REF_DATE desc", nativeQuery = true) 
	 Page<BAMLCustomerChecks> customerchecklist(Pageable page);
	 
	 @Query(value="select count(*) from BAML_ON_BOARD_CHECKS where REF_DATE = ?1 ", nativeQuery = true)
	 Long count(Date searchDate);
	 
	 @Query(value = "select * from BAML_ON_BOARD_CHECKS where REF_DATE = ?1 ", nativeQuery = true) 
	 Page<BAMLCustomerChecks> customerCheckListWithDateFilter(Date searchDate,Pageable page);
	
	

}
