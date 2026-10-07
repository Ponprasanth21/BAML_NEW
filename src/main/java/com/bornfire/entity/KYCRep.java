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
public interface KYCRep extends JpaRepository<KycHistory, String> {

	Optional<KycHistory> findById(String directorId);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_name LIKE %?1%", nativeQuery = true)
	Page<CMG_MASTER> getlistByFIRSTname(Pageable page, String lastname);

	@Query(value = "select * from BAML_KYC_HIST_TABLE where cust_id=?1", nativeQuery = true)
	KycHistory getCustomer(String custid);

	@Query(value = "select * from BAML_KYC_HIST_TABLE order by cust_id DESC ", nativeQuery = true)
	Page<KycHistory> findAllCustom(Pageable pageable);

	@Query(value = "select * from BAML_KYC_HIST_TABLE where cust_id=?1", nativeQuery = true)
	KycHistory getAccountDetailsCust(String CUSTID);

	@Query(value = "select * from BAML_KYC_HIST_TABLE where cust_id=?1 ", nativeQuery = true)
	List<KycHistory> findAllCustIdCustom1(String doc_id);
}