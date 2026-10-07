package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAML_Case_Docs_Rep  extends JpaRepository<BAML_Cust_Case_Docs, String> {
	Optional<BAML_Cust_Case_Docs> findById(String directorId);
	
	@Query(value = "select * from BAML_CUST_CASE_DOCS", nativeQuery = true)
	BAML_Cust_Case_Docs getDoc(String cust_id);
	
	 @Query(value = "select * from BAML_CUST_CASE_DOCS where cust_id =?1 ", nativeQuery = true)
	 BAML_Cust_Case_Docs findByIdCustom(String cust_id);

}
