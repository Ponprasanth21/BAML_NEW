package com.bornfire.entity;

import java.math.BigDecimal;
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
public interface BAML_SCR_Alert_Oper_Repository extends JpaRepository<BAML_SCR_Alert_Oper_Entity, BigDecimal> {

	Optional<BAML_SCR_Alert_Oper_Entity> findById(BigDecimal directorId);

	@Query(value = "select * from BAML_SCR_ALERT_OPER where RULE_REF=?1 and SCR_DATE=?2", nativeQuery = true)
	Page<BAML_SCR_Alert_Oper_Entity> getTransactionDetails(Pageable page,String refno,String date);
	
	
	@Query(value = "select c.cif_id,c.cust_id,a.acid,a.foracid,a.ACCT_NAME,a.ACCT_OPN_DATE,a.SANCT_LIM from BAML_CUST_MAST_TABLE c,BAML_ACCT_MAST_TABLE a\r\n" + 
			"where c.CUST_ID=a.CUST_ID and  a.SCHM_TYPE='LAA' and a.SCHM_CODE=?1 and a.GL_SUB_HEAD_CODE =?2 and  a.ACCT_CLS_FLG='N' and a.ACCT_OPN_DATE=?3  "
			, nativeQuery = true)
	List<Object[]> findAllCustIdforLoans(String schm_code,String glsubheadcode,Date endDate);
//	and c.SUBSEGMENT in('A','B')
	
	@Query(value = "select sum(SANCT_LIM) ,foracid from BAML_ACCT_MAST_TABLE where  ACCT_CLS_FLG='N' and SCHM_TYPE='LAA' and CUST_ID=?1 group by foracid having sum(SANCT_LIM) >= ?2"
			, nativeQuery = true)
	List<Object[]> findAllCustIdforLoansLimit(String custid,BigDecimal ceiling );

	
	
	
	@Query(value = "select c.cif_id,c.cust_id,a.acid,a.foracid,a.ACCT_NAME,a.ACCT_OPN_DATE,a.SANCT_LIM from BAML_CUST_MAST_TABLE c,BAML_ACCT_MAST_TABLE a\r\n" + 
			"where c.CUST_ID=a.CUST_ID and  a.SCHM_TYPE='LAA' and a.SCHM_CODE=?1 and a.GL_SUB_HEAD_CODE =?2 and  a.ACCT_CLS_FLG='N' and a.ACCT_OPN_DATE=?3  and c.occupation =?4"
			, nativeQuery = true)
	List<Object[]> findAllCustIdforLoansCustType(String schm_code,String glsubheadcode,Date endDate,String occupation);
//	and c.SUBSEGMENT in('A','B')
	@Query(value = "select c.cif_id,c.cust_id,a.acid,a.foracid,a.ACCT_NAME,a.ACCT_OPN_DATE,a.SANCT_LIM,a.acct_cls_flg " + 
			"from baml_cust_mast_table c, baml_acct_mast_table a where a.ACCT_CLS_FLG='N' and c.cif_id=a.cif_id  and a.cif_id=?1  "
			, nativeQuery = true)
	List<Object[]> findAllCustIdforLoansCustType_by_custId(String cif);
//****************************************	
	
	@Query(value = "select * from BAML_SCR_ALERT_OPER where DEL_FLG='N' and QUALIFIER=?1 order by tran_date desc", nativeQuery = true) 
	 Page<BAML_SCR_Alert_Oper_Entity> parameterlist(String qualifier,Pageable page);
	 
	 
	 @Query(value = "select * from BAML_SCR_ALERT_OPER where DEL_FLG='N' and tran_date between ?1 and ?2 and QUALIFIER=?3 order by  tran_date ", nativeQuery = true) 
	 Page<BAML_SCR_Alert_Oper_Entity> parameterlistdate(Date Fromdate,Date Todate,String qualifier,Pageable page);

	 @Query(value = "select * from BAML_SCR_ALERT_OPER where AML_TRAN_REF_NO =?1 ", nativeQuery = true) 
	BAML_SCR_Alert_Oper_Entity findByIdcustom(BigDecimal tranid);
	 
	 @Query(value = "select * from BAML_SCR_ALERT_OPER where AML_TRAN_REF_NO =?1 ", nativeQuery = true) 
	 Optional<BAML_SCR_Alert_Oper_Entity> findByIdcustomResult(BigDecimal tranid);
	
}
