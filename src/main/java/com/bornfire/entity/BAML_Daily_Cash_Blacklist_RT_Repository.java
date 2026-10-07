package com.bornfire.entity;


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
public interface BAML_Daily_Cash_Blacklist_RT_Repository extends JpaRepository<BAML_Daily_Cash_Blacklist_RT_Entity, String> {

	Optional<BAML_Daily_Cash_Blacklist_RT_Entity> findById(String directorId);


	@Query(value = "select * from table(BAML_DAILY_CASH_BLACKLIST_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Daily_Cash_Blacklist_RT_Entity> findAllCustIdfordailyCASHReport(String fromdate,String todate,Pageable pageable);
	
	@Query(value = "select * from table(BAML_DAILY_CASH_BLACKLIST_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Daily_Cash_Blacklist_RT_Entity> findAllCustIdfordailyCASHReport(String fromdate,String todate);
	
	@Query(value = "select * from BAML_DAILY_CASH_BLACKLIST_RPT_TEMP_TABLE where tran_date between ?1 and ?2", nativeQuery = true)
	Page<BAML_Daily_Cash_Blacklist_RT_Entity> findAllCustIdfordailyCASHReportPage(String fromdate,String todate,Pageable pageable);
	
}

