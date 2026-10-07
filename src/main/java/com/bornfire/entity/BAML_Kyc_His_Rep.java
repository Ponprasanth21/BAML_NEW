package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BAML_Kyc_His_Rep extends JpaRepository<BAML_Kyc_His_Table, String> {
	Optional<BAML_Kyc_His_Table> findById(String directorId);
	
	
	@Query(value = "select * from BAML_KYC_HIS_TABLE where cust_id=?1", nativeQuery = true)
	BAML_Kyc_His_Table findByCustomId(String custid);
}