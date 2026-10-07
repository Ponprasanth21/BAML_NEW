package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAML_Case_Sheet_Rep extends JpaRepository<BAML_Cust_Case_Sheet, String> {
	Optional<BAML_Cust_Case_Sheet> findById(String directorId);
	
	@Query(value = "select * from BAML_CUST_CASE_SHEET where cust_id=?1", nativeQuery = true)
	BAML_Cust_Case_Sheet  findByIdCustom(String cust_id);
}
