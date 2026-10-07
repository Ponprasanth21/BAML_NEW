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
public interface BAML_Cust_HNWI_RPT_Repository extends JpaRepository<BAML_Cust_HNWI_RPT_Entity, String> {

	Optional<BAML_Cust_HNWI_RPT_Entity> findById(String directorId);

//	this calss is ussed fro hnwi and unsc report (aml monitoring reports)

	@Query(value = "select * from table(BAML_CUST_HNWI_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_HNWI_RPT_Entity> findAllCustHNWIListReport(String fromdate,String todate,Pageable pageable);

	
	@Query(value = "select * from table(BAML_CUST_UNSC_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_HNWI_RPT_Entity> findAllCustUNSClistReport(String fromdate,String todate,Pageable pageable);

	@Query(value = "select * from table(BAML_CUST_HNWI_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Cust_HNWI_RPT_Entity> findAllCustHNWIListReport(String fromdate,String todate);
	
	@Query(value = "select * from table(BAML_CUST_UNSC_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Cust_HNWI_RPT_Entity> findAllCustUNSClistReport(String fromdate,String todate);


}

