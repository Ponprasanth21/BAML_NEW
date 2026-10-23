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

	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where \"RULE_REF\"=?1 and \"SCR_DATE\"=?2", nativeQuery = true)
	Page<BAML_SCR_Alert_Oper_Entity> getTransactionDetails(Pageable page,String refno,String date);
	
	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where \"RULE_REF\" = ?1 or cast(\"AML_TRAN_REF_NO\" as varchar) = ?1", nativeQuery = true)
	List<BAML_SCR_Alert_Oper_Entity> findByRuleRef(String ruleRef);

	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where (\"SCHM_CODE\" = ?1 or \"RULE_REF\" = ?2 or cast(\"AML_TRAN_REF_NO\" as varchar) = ?2)", nativeQuery = true)
	List<BAML_SCR_Alert_Oper_Entity> findBySchmOrRule(String schmCode, String ruleRef);

	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" order by \"AML_TRAN_REF_NO\" desc", nativeQuery = true)
	List<BAML_SCR_Alert_Oper_Entity> findAllAlerts();
	
	@Query(value = "select c.\"CIF_ID\",c.\"CUST_ID\",a.\"ACID\",a.\"FORACID\",a.\"ACCT_NAME\",a.\"ACCT_OPN_DATE\",a.\"SANCT_LIM\" from \"AML\".\"BAML_CUST_MAST_TABLE\" c, \"AML\".\"BAML_ACCT_MAST_TABLE\" a " + 
			"where c.\"CUST_ID\"=a.\"CUST_ID\" and trim(a.\"ACCT_CLS_FLG\")='N' and (?1 is null or ?1 = '' or a.\"SCHM_CODE\"=?1) and (?2 is null or ?2 = '' or a.\"GL_SUB_HEAD_CODE\"=?2) and (?3 is null or a.\"ACCT_OPN_DATE\"=?3)", nativeQuery = true)
	List<Object[]> findAllCustIdforLoans(String schm_code,String glsubheadcode,Date endDate);
	
	@Query(value = "select sum(a.\"SANCT_LIM\"), a.\"FORACID\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" a where trim(a.\"ACCT_CLS_FLG\")='N' and a.\"CUST_ID\"=?1 group by a.\"FORACID\" having (?2 is null or sum(a.\"SANCT_LIM\") >= ?2)", nativeQuery = true)
	List<Object[]> findAllCustIdforLoansLimit(String custid,BigDecimal ceiling );
	
	@Query(value = "select c.\"CIF_ID\",c.\"CUST_ID\",a.\"ACID\",a.\"FORACID\",a.\"ACCT_NAME\",a.\"ACCT_OPN_DATE\",a.\"SANCT_LIM\" from \"AML\".\"BAML_CUST_MAST_TABLE\" c, \"AML\".\"BAML_ACCT_MAST_TABLE\" a " + 
			"where c.\"CUST_ID\"=a.\"CUST_ID\" and trim(a.\"ACCT_CLS_FLG\")='N' and (?1 is null or ?1 = '' or a.\"SCHM_CODE\"=?1) and (?2 is null or ?2 = '' or a.\"GL_SUB_HEAD_CODE\"=?2) and (?3 is null or a.\"ACCT_OPN_DATE\"=?3) and (?4 is null or ?4 = '' or c.\"OCCUPATION\" =?4)", nativeQuery = true)
	List<Object[]> findAllCustIdforLoansCustType(String schm_code,String glsubheadcode,Date endDate,String occupation);

	@Query(value = "select c.\"CIF_ID\",c.\"CUST_ID\",a.\"ACID\",a.\"FORACID\",a.\"ACCT_NAME\",a.\"ACCT_OPN_DATE\",a.\"SANCT_LIM\",a.\"ACCT_CLS_FLG\" " + 
			"from \"AML\".\"BAML_CUST_MAST_TABLE\" c, \"AML\".\"BAML_ACCT_MAST_TABLE\" a where trim(a.\"ACCT_CLS_FLG\")='N' and c.\"CIF_ID\"=a.\"CIF_ID\" and a.\"CIF_ID\"=?1", nativeQuery = true)
	List<Object[]> findAllCustIdforLoansCustType_by_custId(String cif);
	
	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null) and (\"QUALIFIER\"=?1 or (?1='S' and (\"QUALIFIER\"='S' or \"FORACID\" like '%LN%' or \"SCHM_CODE\" is not null))) order by \"TRAN_DATE\" desc", nativeQuery = true) 
	Page<BAML_SCR_Alert_Oper_Entity> parameterlist(String qualifier,Pageable page);
	 
	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null) and \"TRAN_DATE\" between ?1 and ?2 and (\"QUALIFIER\"=?3 or (?3='S' and (\"QUALIFIER\"='S' or \"FORACID\" like '%LN%' or \"SCHM_CODE\" is not null))) order by \"TRAN_DATE\"", nativeQuery = true) 
	Page<BAML_SCR_Alert_Oper_Entity> parameterlistdate(Date Fromdate,Date Todate,String qualifier,Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where \"AML_TRAN_REF_NO\" =?1 ", nativeQuery = true) 
	BAML_SCR_Alert_Oper_Entity findByIdcustom(BigDecimal tranid);
	 
	@Query(value = "select * from \"AML\".\"BAML_SCR_ALERT_OPER\" where \"AML_TRAN_REF_NO\" =?1 ", nativeQuery = true) 
	Optional<BAML_SCR_Alert_Oper_Entity> findByIdcustomResult(BigDecimal tranid);
}
