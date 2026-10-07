package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAML_Doc_Hist_Rep extends JpaRepository<BAML_Doc_Hist_Table, String> {
	Optional<BAML_Doc_Hist_Table> findById(String directorId);

	@Query(value = "select * from BAML_DOC_HIST_TABLE where cust_id = ?1", nativeQuery = true)
	BAML_Doc_Hist_Table  findByCustId(String custid);

}