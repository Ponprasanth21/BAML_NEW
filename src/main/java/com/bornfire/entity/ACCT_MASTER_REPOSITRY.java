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
public interface ACCT_MASTER_REPOSITRY extends JpaRepository<ACCT_MASTER, String> {
	Optional<ACCT_MASTER> findById(String directorId);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET' AND \"CUST_ID\" = ?1 ORDER BY \"CUST_ID\" DESC",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET' AND \"CUST_ID\" = ?1",
			nativeQuery = true)
	Page<ACCT_MASTER> findAllCustIdCustom(String custId, Pageable pageable);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET' AND \"CUST_ID\" IS NOT NULL ORDER BY \"CUST_ID\" DESC",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET' AND \"CUST_ID\" IS NOT NULL",
			nativeQuery = true)
	Page<ACCT_MASTER> findAllAcctIdCustom(Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACID\"=?1 or \"FORACID\"=?1 limit 1", nativeQuery = true)
	ACCT_MASTER getAccountDetails(String acctnum);
	
	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='GL' and \"REF_CODE\" in (select \"GL_SUB_HEAD_CODE\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where (\"CIF_ID\"=?1 or \"CUST_ID\"=?1) and \"FORACID\"=?2) limit 1", nativeQuery = true)
	String getglsubheadcode(String custid,String acctnum);
	
	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='SC' and \"REF_CODE\" in (select \"SCHM_CODE\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where (\"CIF_ID\"=?1 or \"CUST_ID\"=?1) and \"FORACID\"=?2) limit 1", nativeQuery = true)
	String getschmcode(String custid,String acctnum);
	
	@Query(value = "select maturity_date from baml_tam where acid=?1", nativeQuery = true)
	Date getMaturityDate(String acid);
	
	@Query(value = "select REP_PERD_MTHS from baml_lam where acid=?1", nativeQuery = true)
	String getLOANPERIOD(String acid);
	
	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='TYPE_ADVN' and \"REF_CODE\" in (select \"TYPE_OF_ADVN\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACID\"=?1 or \"FORACID\"=?1) limit 1", nativeQuery = true)
	String getDESTINATIONOFFUND(String acid);
	
	
	@Query(value = "select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"FORACID\"=?1 or \"ACID\"=?1 limit 1", nativeQuery = true)
	ACCT_MASTER getAuditAccount(String acctnum);
	
	@Query(value="select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1",nativeQuery = true)
	List<ACCT_MASTER> getAuditCustomer(String custId);
 	
	@Query(value = "select cast(\"CLR_BAL_AMT\" as varchar) as \"TRAN_DATE_BAL\", to_char(\"ACCT_OPN_DATE\",'dd/MM/yyyy') as \"EOD_DATE\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACID\"=?1 or \"FORACID\"=?1 limit 1", nativeQuery = true)
	List<Object[]> getAccountDetails1(String acctnum);
	
	@Query(value = "select \"GL_SUB_HEAD_CODE\", \"SCHM_CODE\", \"ACCT_NAME\", \"FORACID\", to_char(\"ACCT_OPN_DATE\",'dd/MM/yyyy') as \"ACCT_OPN_DATE\", to_char(\"ACCT_CLS_DATE\",'dd/MM/yyyy') as \"ACCT_CLS_DATE\", \"ACCT_STATUS\", to_char(\"ACCT_STATUS_DATE\",'dd/MM/yyyy') as \"ACCT_STATUS_DATE\", \"MODE_OF_OPER_CODE\", CAST(\"CLR_BAL_AMT\" AS VARCHAR) as \"CLR_BAL_AMT\", to_char(\"LAST_TRAN_DATE\",'dd/MM/yyyy') as \"LAST_TRAN_DATE\", \"ACID\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1", nativeQuery = true)
	List<Object[]> findAllCustIdCustom1(String acctnum);
/*	AND SYSDATE BETWEEN B.EOD_DATE AND B.END_EOD_DATE*/
	/*@Query(value = "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id =?1) ", nativeQuery = true)
	List<ACCT_MASTER> findAllCustIdCustom1(String AcctNumber);*/
	@Query(value = "select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACCT_OWNERSHIP\" !='O' and \"ACID\" in (select \"ACID\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACCT_OCCP_CODE\" is null or \"PURPOSE_OF_ADVN\" is null or \"CUST_ID\" is not null) limit 1000", nativeQuery = true)
	List<ACCT_MASTER> findAllCustom1();

	@Query(value = "select count(*) from \"AML\".\"BAML_ACCT_MAST_TABLE\" where (\"CIF_ID\" = ?1 or \"CUST_ID\" = ?1) and \"SCHM_TYPE\" in ('TUA','TDA') and \"ACCT_CLS_FLG\" != 'Y'", nativeQuery = true)
	String getCountOfDeposit(String AcctNumber);
	
	@Query(value = "select count(*) from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1", nativeQuery = true)
	String countCustom(String AcctNumber);
	
	@Query(value = "select \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1 limit 1", nativeQuery = true)
	String getAuditcustid(String custid);
	
	@Query(value = "select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"FORACID\" = ?1 or \"ACID\" = ?1 limit 1", nativeQuery = true)
	ACCT_MASTER getacctdetailsfroname(String foracid);

	@Query(value = "select * from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACID\" = ?1 limit 1", nativeQuery = true)
	ACCT_MASTER getacctdetailsfronameacid(String acid);
	
	@Query(value = "select \"ACCT_OPN_DATE\" from \"AML\".\"BAML_ACCT_MAST_TABLE\" where \"ACID\" = ?1 limit 1", nativeQuery = true)
	Date getacctopndate(String acid);

	/************************************************************************SEARCH ACCOUNT INQUIRY*********************************************************************/

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"CIF_ID\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getcustomerId(String custId);

	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"CIF_ID\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	String getcustomerIdcount(String custId);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"FORACID\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCNumber(String custId);
	
	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_NAME\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCName(String custId);
	
	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"SCHM_CODE\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCSchCode(String custId);
	
	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CRNCY_CODE\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCCurCode(String custId);
	
	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"CLR_BAL_AMT\" = ?1 AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCBalance(String custId);
	
	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"FORACID\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	String getACCNumbercount(String custId);
	
	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_NAME\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	String getACCNamecount(String custId);
	
	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"SCHM_CODE\" LIKE %?1% AND \"ACCT_CLS_FLG\" = 'N' AND \"SCHM_CODE\" != 'LASET'", nativeQuery = true)
	String getACCSchCodecount(String custId);
	
	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"ACCT_CRNCY_CODE\" LIKE %?1%", nativeQuery = true)
	String getACCCurCodecount(String custId);
	
	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCT_MAST_TABLE\" WHERE \"CLR_BAL_AMT\" = ?1", nativeQuery = true)
	String getACCBalancecount(String custId);

	/************************************************************************SEARCH ACCOUNT INQUIRY*********************************************************************/

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acct_name LIKE %?1%  AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getAuditAcctName(String custId);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acct_short_name LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getAuditShortAcctName(String custId);
	
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where PREFERREDEMAIL =?1) AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getAuditMailId (String PREFERREDEMAIL);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM =?1) AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getAuditNatId(String NAT_ID_CARD_NUM);
	
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where PREFERREDPHONE =?1) AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getAuditMobNo(String PREFERREDPHONE);
	
	
	
	
}
