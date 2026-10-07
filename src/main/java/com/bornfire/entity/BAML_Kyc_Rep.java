package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BAML_Kyc_Rep extends JpaRepository<BAML_Kyc_Table, String> {
	Optional<BAML_Kyc_Table> findById(String directorId);

	@Query(value = "select * from BAML_KYC_TABLE order by kyc_review_date ", nativeQuery = true)
	Page<BAML_Kyc_Table> findAllCustom(Pageable pageable);

	@Query(value = "select * from BAML_KYC_TABLE where cust_id=?1", nativeQuery = true)
	BAML_Kyc_Table getCustomer(String custid);
	
	@Query(value="select * from BAML_KYC_TABLE where cust_id=?1",nativeQuery = true)
	Page<BAML_Kyc_Table> findAllCustIdCustom(String custId,Pageable pageable);

	@Query(value = "select count(*) from BAML_KYC_TABLE", nativeQuery = true)
	long findCustomcount();
	
	
	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_name LIKE %?1%", nativeQuery = true)
	Page<CMG_MASTER> getlistByFIRSTname(Pageable page, String name);
	
	
	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_id=?1", nativeQuery = true)
	CMG_MASTER getAccountDetailsCust(String CUSTID);
	

}