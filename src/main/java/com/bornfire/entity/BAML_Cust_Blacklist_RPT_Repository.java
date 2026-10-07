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
public interface BAML_Cust_Blacklist_RPT_Repository extends JpaRepository<BAML_Cust_Blacklist_RPT_Entity, String> {

	Optional<BAML_Cust_Blacklist_RPT_Entity> findById(String directorId);

	//this class is used for aml monitoring reports - black list ind,corp, negatoive list 

	@Query(value = "select * from table(BAML_CUST_BLACKLIST_IND_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_Blacklist_RPT_Entity> findAllCustBlackListIndReport(String fromdate,String todate,Pageable pageable);

	@Query(value = "select * from table(BAML_CUST_BLACKLIST_CORP_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_Blacklist_RPT_Entity> findAllCustBlackListCorpReport(String fromdate,String todate,Pageable pageable);
	
	@Query(value = "select * from table(BAML_CUST_NEGATIVE_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_Blacklist_RPT_Entity> findAllCustNegativelistReport(String fromdate,String todate,Pageable pageable);

	@Query(value = "select * from table(BAML_CUST_WHITE_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_Blacklist_RPT_Entity> findAllCustWhitelistReport(String fromdate,String todate,Pageable pageable);


	@Query(value = "select * from table(BAML_CUST_BLACKLIST_CORP_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Cust_Blacklist_RPT_Entity> findAllCustBlackListCorpReport(String fromdate,String todate);
	
	@Query(value = "select * from table(BAML_CUST_BLACKLIST_IND_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Cust_Blacklist_RPT_Entity> findAllCustBlackListIndReport(String fromdate,String todate);

	
}

