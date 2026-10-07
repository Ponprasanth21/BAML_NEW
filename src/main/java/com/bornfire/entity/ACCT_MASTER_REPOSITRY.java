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

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where ACCT_CLS_FLG='N'  and schm_code !='LASET' and cust_id=?1 order by cust_id desc", nativeQuery = true)
	Page<ACCT_MASTER> findAllCustIdCustom(String custId, Pageable pageable);

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where ACCT_CLS_FLG='N' and schm_code !='LASET' and cust_id is not null order by cust_id desc", nativeQuery = true)
	Page<ACCT_MASTER> findAllAcctIdCustom(Pageable pageable);

	@Query(value = "select * from BAML_ACCT_MAST_TABLE  where acid=?1", nativeQuery = true)
	ACCT_MASTER getAccountDetails(String acctnum);
	
	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='GL' and ref_code in (select gl_sub_head_code from BAML_ACCT_MAST_TABLE where cIF_ID=?1 and foracid=?2)", nativeQuery = true)
	String getglsubheadcode(String custid,String acctnum);
	
	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='SC' and ref_code in (select schm_code from BAML_ACCT_MAST_TABLE where cIF_ID=?1 and foracid=?2)", nativeQuery = true)
	String getschmcode(String custid,String acctnum);
	
	@Query(value = "select maturity_date from baml_tam where acid=?1", nativeQuery = true)
	Date getMaturityDate(String acid);
	
	@Query(value = "select REP_PERD_MTHS from baml_lam where acid=?1", nativeQuery = true)
	String getLOANPERIOD(String acid);
	
	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='TYPE_ADVN' and ref_code in (select TYPE_OF_ADVN from BAML_ACCT_MAST_TABLE where acid=?1)", nativeQuery = true)
	String getDESTINATIONOFFUND(String acid);
	
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE  where foracid=?1", nativeQuery = true)
	ACCT_MASTER getAuditAccount(String acctnum);
	
	@Query(value="select * from BAML_ACCT_MAST_TABLE where cif_id = ?1",nativeQuery = true)
	List<ACCT_MASTER> getAuditCustomer(String custId);
 	
	
	@Query(value = "select   B.TRAN_DATE_BAL,to_char(B.EOD_DATE,'dd/mm/yyyy')EOD_DATE from BAML_ACCT_MAST_TABLE A,BAML_ACCT_BAL_TABLE B where A.ACID = B.ACID AND SYSDATE BETWEEN B.EOD_DATE AND B.END_EOD_DATE AND B.acid=?1", nativeQuery = true)
	List<Object[]> getAccountDetails1(String acctnum);
	
	
	@Query(value = "select A.gl_sub_head_code,A.SCHM_CODE,A.ACCT_NAME,A.FORACID,to_char(A.ACCT_OPN_DATE,'dd/mm/yyyy')ACCT_OPN_DATE,to_char(A.ACCT_CLS_DATE,'dd/mm/yyyy')ACCT_CLS_DATE,A.ACCT_STATUS,to_char(A.ACCT_STATUS_DATE,'dd/mm/yyyy')ACCT_STATUS_DATE,A.MODE_OF_OPER_CODE,B.TRAN_DATE_BAL,to_char(B.EOD_DATE,'dd/mm/yyyy')EOD_DATE,A.ACID from BAML_ACCT_MAST_TABLE A,BAML_ACCT_BAL_TABLE B where A.ACID = B.ACID AND SYSDATE BETWEEN B.EOD_DATE AND B.END_EOD_DATE AND a.cif_id=?1", nativeQuery = true)
	List<Object[]> findAllCustIdCustom1(String acctnum);
/*	AND SYSDATE BETWEEN B.EOD_DATE AND B.END_EOD_DATE*/
	/*@Query(value = "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id =?1) ", nativeQuery = true)
	List<ACCT_MASTER> findAllCustIdCustom1(String AcctNumber);*/
	@Query(value = "select * from baml_acct_mast_table where acct_ownership !='O'  and acid in  (select acid from baml_acct_mast_table where ACCT_OCCP_CODE is null or PURPOSE_OF_ADVN is null or cust_id is not null ) and  rownum < 1000 ", nativeQuery = true)
	List<ACCT_MASTER> findAllCustom1();

	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where cif_id =?1 and schm_type in ('TUA','TDA') and acct_cls_flg!='Y'", nativeQuery = true)
	String getCountOfDeposit(String AcctNumber);
	
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id =?1)", nativeQuery = true)
	String countCustom(String AcctNumber);

	
	@Query(value = "select cust_id from BAML_CUST_MAST_TABLE where cif_id =?1", nativeQuery = true)
	String getAuditcustid(String custid);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acid=?1", nativeQuery = true)
	ACCT_MASTER getacctdetailsfroname(String foracid);

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acid=?1", nativeQuery = true)
	ACCT_MASTER getacctdetailsfronameacid(String acid);

	
	@Query(value = "select acct_opn_date from BAML_ACCT_MAST_TABLE where acid=?1", nativeQuery = true)
	Date getacctopndate(String acid);

	/************************************************************************SEARCH ACCOUNT INQUIRY*********************************************************************/

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where cif_id LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getcustomerId(String custId);

	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where cif_id LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	String getcustomerIdcount(String custId);

	@Query(value = "select * from BAML_ACCT_MAST_TABLE where foracid LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCNumber(String custId);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acct_name LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCName(String custId);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where schm_code LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET' ", nativeQuery = true)
	List<ACCT_MASTER> getACCSchCode(String custId);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where acct_crncy_code LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCCurCode(String custId);
	
	@Query(value = "select * from BAML_ACCT_MAST_TABLE where clr_bal_amt= ?1 AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	List<ACCT_MASTER> getACCBalance(String custId);
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where foracid LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	String getACCNumbercount(String custId);
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where acct_name LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	String getACCNamecount(String custId);
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where schm_code LIKE %?1% AND ACCT_CLS_FLG='N'  and schm_code !='LASET'", nativeQuery = true)
	String getACCSchCodecount(String custId);
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where acct_crncy_code LIKE %?1% ", nativeQuery = true)
	String getACCCurCodecount(String custId);
	
	@Query(value = "select count(*) from BAML_ACCT_MAST_TABLE where clr_bal_amt= ?1 ", nativeQuery = true)
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
