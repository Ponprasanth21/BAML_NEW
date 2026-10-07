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
public interface BAML_Cust_PEP_RPT_Repository extends JpaRepository<BAML_Cust_PEP_RPT_Entity, String> {

	Optional<BAML_Cust_PEP_RPT_Entity> findById(String directorId);


	@Query(value = "select * from table(BAML_CUST_PEP_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	Page<BAML_Cust_PEP_RPT_Entity> findAllCustPEPListReport(String fromdate,String todate,Pageable pageable);

	@Query(value = "select * from table(BAML_CUST_PEP_LIST_RPT_FUN(?1,?2))", nativeQuery = true)
	List<BAML_Cust_PEP_RPT_Entity> findAllCustPEPListReport(String fromdate,String todate);

	
}

