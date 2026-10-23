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
public interface CMG_MASTER_REPOSITRY extends JpaRepository<CMG_MASTER, String> {
	Optional<CMG_MASTER> findById(String directorId);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" ORDER BY \"CUST_ID\" DESC",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_CUST_MAST_TABLE\"",
			nativeQuery = true)
	Page<CMG_MASTER> findAllCustom(Pageable pageable);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cif_id=?1", nativeQuery = true)
	CMG_MASTER getcustId(String cust_id);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cif_id=?1", nativeQuery = true)
	CMG_MASTER getcustName(String cust_id);

	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='43' and ref_code in (select cust_type_code from BAML_CUST_MAST_TABLE where cif_id=?1)", nativeQuery = true)
	String getcustType(String cust_id);

	/*
	 * @Query(value = "select localetext from BAML_CUST_MAST_TABLE where cif_id=?1",
	 * nativeQuery = true) String getcustrating(String cust_id);
	 */
	@Query(value = "select * from BCM where nat_id_card_num=?1", nativeQuery = true)
	CMG_MASTER findByIdnic(String cust_id);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM=?1 ", nativeQuery = true)
	CMG_MASTER getnational_id_card_num(String national_id_card_num);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where PREFERREDPHONE LIKE %?1%", nativeQuery = true)
	List<CMG_MASTER> getmob_no(String mob_no);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where PREFERREDEMAIL=?1 ", nativeQuery = true)
	CMG_MASTER getmail_id(String mail_id);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_short_name LIKE %?1% ", nativeQuery = true)
	List<CMG_MASTER> getcust_short_name(String CUST_SHORT_NAME);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_name LIKE  %?1% ", nativeQuery = true)
	List<CMG_MASTER> getcustname(String custname);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"ANNUAL_SALARY_INCOME\" is null or \"ANNUAL_SALARY_INCOME\" = 0 or \"SOURCEOFINCOME\" is null or \"PLACEOFBIRTH\" is null or \"OCCUPATION\" is null or \"COUNTRY_OF_BIRTH\" is null order by \"CUST_OPN_DATE\" desc limit 1100", nativeQuery = true)
	List<CMG_MASTER> findAllCustom1();

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where (\"CIF_ID\" = ?1 or \"CUST_ID\" = ?1) and (\"ANNUAL_SALARY_INCOME\" is null or \"ANNUAL_SALARY_INCOME\" = 0 or \"SOURCEOFINCOME\" is null or \"PLACEOFBIRTH\" is null or \"OCCUPATION\" is null or \"COUNTRY_OF_BIRTH\" is null) order by \"CUST_OPN_DATE\" desc limit 1100", nativeQuery = true)
	List<CMG_MASTER> findAllCustom1(String cust_id);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where PREFERREDPHONE=?1", nativeQuery = true)
	CMG_MASTER getByMob(String mob_no);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM=?1", nativeQuery = true)
	CMG_MASTER getByNID(String national_id_card_num);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where PREFERREDEMAIL=?1", nativeQuery = true)
	CMG_MASTER getByMail(String mail_id);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where CUST_SHORT_NAME=?1", nativeQuery = true)
	CMG_MASTER getByShort(String CUST_NAME);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1 limit 1", nativeQuery = true)
	CMG_MASTER getAuditCustomer(String custId);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where UPPER(\"CUST_NAME\") like UPPER(?1) limit 1", nativeQuery = true)
	CMG_MASTER getAuditCustomerName(String cust_name);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where UPPER(\"CUST_SHORT_NAME\") like UPPER(?1)", nativeQuery = true)
	List<CMG_MASTER> getAuditCustomerSName(String cust_name);

	/*
	 * @Query(value =
	 * "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where PREFERREDEMAIL =?1)"
	 * , nativeQuery = true) List<ACCT_MASTER> getAuditMailId (String
	 * PREFERREDEMAIL);
	 */

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CUST_ID\"=?1 or \"CIF_ID\"=?1", nativeQuery = true)
	Page<CMG_MASTER> findAllCustIdCustom(String custId, Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1 limit 1", nativeQuery = true)
	CMG_MASTER getCustomer(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='DEP' and \"REF_CODE\" in (select \"STRUSERFIELD10\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getDepartment(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='PAYS' and \"REF_CODE\" in (select \"STRUSERFIELD11\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getPaysiteCode(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='CS' and \"REF_CODE\" in (select \"CUST_STAT_CODE\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getCustomerStatus(String custid);

	@Query(value = "select \"DTDATE1\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1 limit 1", nativeQuery = true)
	Date getcustopendate(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='BABR' and \"REF_CODE\" in (select \"STRUSERFIELD13\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getBankBranch(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='BK' and \"REF_CODE\" in (select SUBSTR(\"STRUSERFIELD13\",1,2) from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getBankName(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='21' and \"REF_CODE\" in (select \"OCCUPATION\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getOccupation(String custid);

	@Query(value = "select \"REF_CODE\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='21' and upper(trim(\"REF_DESC\")) = ?1 limit 1", nativeQuery = true)
	String getOccupationcode(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='01' and \"REF_CODE\" in (select \"CITY_CODE\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getcitycode(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='02' and \"REF_CODE\" in (select \"STATE_CODE\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getstatecode(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='03' and \"REF_CODE\" in (select \"CNTRY_CODE\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getcountrycode(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='03' and \"REF_CODE\" in (select \"RESIDENCE_COUNTRY\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getcountryresidence(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='01' and \"REF_CODE\" in (select \"PLACEOFBIRTH\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getplaceofbirth(String custid);

	@Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='43' and \"REF_CODE\" in (select \"CUST_TYPE_CODE\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1) limit 1", nativeQuery = true)
	String getcustomertype(String custid);

	@Query(value = "select * from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\"=?1 or \"CUST_ID\"=?1 limit 1", nativeQuery = true)
	CMG_MASTER getAccountDetailsCust(String CUSTID);

	@Query(value = "select distinct cust_id,cust_name,riskrating from CUST_MAST_GEN_TABLE ", nativeQuery = true)
	List<Object[]> findAllCustom();

	/*********************************************
	 * SEARCH DETAILS
	 *********************************************/

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"CIF_ID\" LIKE %?1%", nativeQuery = true)
	List<CMG_MASTER> getcustomerId(String custid2);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE UPPER(\"CUST_NAME\") LIKE UPPER(?1)", nativeQuery = true)
	List<CMG_MASTER> getcustomerName(String custName2);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE UPPER(\"RESIDING_COUNTRY\") LIKE UPPER(?1)", nativeQuery = true)
	List<CMG_MASTER> getcustomerDOB(String custName2);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"PREFERREDPHONE\" LIKE %?1%", nativeQuery = true)
	List<CMG_MASTER> getcustomerNUMBER(String custName2);

	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"PREFERREDPHONE\" LIKE %?1%", nativeQuery = true)
	String getcustomerNUMBERcount(String custName2);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"PREFERREDEMAIL\" LIKE %?1%", nativeQuery = true)
	List<CMG_MASTER> getcustomerEMAIL(String custName2);

	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"PREFERREDEMAIL\" LIKE %?1%", nativeQuery = true)
	String getcustomerEMAILcount(String custName2);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"NAT_ID_CARD_NUM\" LIKE %?1%", nativeQuery = true)
	List<CMG_MASTER> getcustomerNatID(String custName2);

	@Query(value = "SELECT COUNT(*) FROM \"AML\".\"BAML_CUST_MAST_TABLE\" WHERE \"NAT_ID_CARD_NUM\" LIKE %?1%", nativeQuery = true)
	String getcustomerNatIDcount(String custName2);

//		888888*************************************************8report

	@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME "
			+ "from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t "
			+ "where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('LAA','CLA') "
			+ "and tran_date between ?1 and ?2 ", nativeQuery = true)
	List<Object[]> findAllCustIdfordailyLoanReport(Date startDate, Date endDate);

	@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME "
			+ "from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t "
			+ "where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('SBA','CAA','ODA') "
			+ "and tran_date between ?1 and ?2 ", nativeQuery = true)
	List<Object[]> findAllCustIdfordailyRSSReport(Date startDate, Date endDate);

	@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME "
			+ "from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t "
			+ "where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('TDA','TUA') "
			+ "and tran_date between ?1 and ?2 ", nativeQuery = true)
	List<Object[]> findAllCustIdfordailyDepositReport(Date startDate, Date endDate);

	@Query(value = "select c.cif_id,c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,t.TRAN_DATE,t.TRAN_AMT,c.CUST_FIRST_NAME from BAML_CUST_MAST_TABLE c ,BAML_ACCT_MAST_TABLE a left outer join baml_acct_trans_table t on a.acid=t.acid where a.cust_id=c.cust_id and TRAN_TYPE = 'C'  and TRAN_SUB_TYPE IN ('NR','NP') and tran_date between ?1 and ?2 order by TRAN_DATE DESC ", nativeQuery = true)
	List<Object[]> findAllCustIdfordailyCASHReport(Date startdate, Date endDate);

	// Dashboard Query//
	@Query(value = "SELECT \"CUSTOMER_COUNT\" " + "FROM \"AML\".\"BAML_DASHBOARD\"", nativeQuery = true)
	long findCustomcount();

	@Query(value = "SELECT \"ACCOUNT_COUNT\" " + "FROM \"AML\".\"BAML_DASHBOARD\"", nativeQuery = true)
	long findAcccount();

	@Query(value = "SELECT \"TRANSACTION_COUNT\" " + "FROM \"AML\".\"BAML_DASHBOARD\"", nativeQuery = true)
	long findTranCount();

	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='43' and ref_code in (select cust_type_code from BAML_CUST_MAST_TABLE where nat_id_card_num=?1 and city_code=?2)", nativeQuery = true)
	CMG_MASTER getCity(String nid, String city_code);

	@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='01' and ref_code in (select placeofbirth from BAML_CUST_MAST_TABLE where  placeofbirth=?1)", nativeQuery = true)
	String getPOBDesc(String pob);

	@Query(value = "select a.cust_first_name,a.cust_last_name,a.cust_name,a.residing_country,a.address1,a.address2,b.ref_desc from BAML_CUST_MAST_TABLE a,baml_reference_code_table b where a.nat_id_card_num=and b.ref_rec_type ='01' and b.ref_code = a.placeofbirth", nativeQuery = true)
	List<Object> getNidFetchDet(String nid);

	@Query(value = "select count(*) from BAML_CUST_MAST_TABLE where nat_id_card_num=?1", nativeQuery = true)
	int getblackListBCM(String nid2);

	@Query(value = "select cust_name from BAML_CUST_MAST_TABLE where cust_first_name=?1 and cust_last_name=?2", nativeQuery = true)
	String findByFirstName(String custname, String custname1);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_middle_name=?1", nativeQuery = true)
	String findBySecondName(String custname);

	@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_last_name=?1", nativeQuery = true)
	String findByThirdName(String custname);
}