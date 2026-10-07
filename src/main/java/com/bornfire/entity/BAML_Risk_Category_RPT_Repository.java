package com.bornfire.entity;


import java.util.Date;
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
public interface BAML_Risk_Category_RPT_Repository extends JpaRepository<BAML_Risk_Category_RPT_Entity, String> {

	Optional<BAML_Risk_Category_RPT_Entity> findById(String directorId);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='Loan' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getLoanData(String fromdate,String todate,String risk_category,Pageable pageable);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='RSS' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getRSSData(Pageable pageable,Date fromdate,Date todate,String risk_category);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='Deposit' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getDepositData(Pageable pageable,Date fromdate,Date todate,String risk_category);
	
	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='Loan' and RISK_CATEGORY=?1", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getLoanData(Pageable pageable,String risk_category);
	
	@Query(value = "select * from BAML_RISK_REPORTS where rpt_type='RSS' and RISK_CATEGORY=?1", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getRSSData(Pageable pageable,String risk_category);

	@Query(value = "select * from BAML_RISK_REPORTS where rpt_type='Deposit' and RISK_CATEGORY=?1", nativeQuery = true)
	Page<BAML_Risk_Category_RPT_Entity> getDepositData(Pageable pageable,String risk_category);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='Loan' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	List<BAML_Risk_Category_RPT_Entity> getLoanData(String fromdate,String todate,String risk_category);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='RSS' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	List<BAML_Risk_Category_RPT_Entity> getRSSData(String fromdate,String todate,String risk_category);

	@Query(value = "select * from BAML_RISK_REPORTS where RPT_TYPE='Deposit' and ACCT_OPN_DATE between ?1 and ?2 and RISK_CATEGORY=?3", nativeQuery = true)
	List<BAML_Risk_Category_RPT_Entity> getDepositData(String fromdate,String todate,String risk_category);
	
}

