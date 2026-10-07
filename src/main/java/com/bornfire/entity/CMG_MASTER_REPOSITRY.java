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
public interface CMG_MASTER_REPOSITRY extends JpaRepository<CMG_MASTER,String> {
	 Optional<CMG_MASTER> findById( String directorId);
	
	    @Query(value="select * from BAML_CUST_MAST_TABLE order by cust_id DESC",nativeQuery = true)
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
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where ANNUAL_SALARY_INCOME !='0' and cust_id in  (select cust_id from BAML_CUST_MAST_TABLE where ANNUAL_SALARY_INCOME is null or SOURCEOFINCOME is null or PLACEOFBIRTH is null or OCCUPATION is null or COUNTRY_OF_BIRTH is null)and rownum < 1100 order by cust_opn_date desc", nativeQuery = true)
		List<CMG_MASTER> findAllCustom1(String cust_id);

	 	@Query(value="select * from BAML_CUST_MAST_TABLE where PREFERREDPHONE=?1",nativeQuery = true)
		CMG_MASTER getByMob(String mob_no);
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM=?1",nativeQuery = true)
		CMG_MASTER getByNID(String national_id_card_num);
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where PREFERREDEMAIL=?1",nativeQuery = true)
		CMG_MASTER getByMail(String mail_id);
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where CUST_SHORT_NAME=?1",nativeQuery = true)
		CMG_MASTER getByShort(String CUST_NAME);
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where cif_id=?1",nativeQuery = true)
		CMG_MASTER getAuditCustomer(String custId);
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where UPPER(cust_name) like UPPER(?1)",nativeQuery = true)
	 	CMG_MASTER getAuditCustomerName(String cust_name);
	 	
	 	
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where UPPER(cust_short_name) like UPPER(?1)",nativeQuery = true)
		List<CMG_MASTER> getAuditCustomerSName(String cust_name);
		
	/*
	 * @Query(value =
	 * "select * from BAML_ACCT_MAST_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where PREFERREDEMAIL =?1)"
	 * , nativeQuery = true) List<ACCT_MASTER> getAuditMailId (String
	 * PREFERREDEMAIL);
	 */
	 
	 	@Query(value="select * from BAML_CUST_MAST_TABLE where cust_id=?1",nativeQuery = true)
		Page<CMG_MASTER> findAllCustIdCustom(String custId,Pageable pageable);

		
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where cIF_ID=?1", nativeQuery = true)
		CMG_MASTER getCustomer(String custid);
		
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='DEP' and ref_code in (select struserfield10 from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getDepartment(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='PAYS' and ref_code in (select struserfield11 from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getPaysiteCode(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='CS' and ref_code in (select cust_stat_code from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getCustomerStatus(String custid);
		
		@Query(value = "select dtdate1 from BAML_CUST_MAST_TABLE where cIF_ID=?1", nativeQuery = true)
		Date getcustopendate(String custid);
	
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='BABR' and ref_code in (select struserfield13 from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getBankBranch(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='BK' and ref_code in (select SUBSTR(struserfield13,1,2) from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getBankName(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='21' and ref_code in (select occupation from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getOccupation(String custid);
		
		

		@Query(value = "select ref_code from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='21' and upper(trim(ref_desc)) = ?1", nativeQuery = true)
		String getOccupationcode(String custid);
		
		
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='01' and ref_code in (select city_code from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getcitycode(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='02' and ref_code in (select state_code from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getstatecode(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='03' and ref_code in (select cntry_code from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getcountrycode(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='03' and ref_code in (select residence_country from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getcountryresidence(String custid);
		
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='01' and ref_code in (select placeofbirth from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getplaceofbirth(String custid);
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='43' and ref_code in (select cust_type_code from BAML_CUST_MAST_TABLE where cIF_ID=?1)", nativeQuery = true)
		String getcustomertype(String custid);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where cif_id=?1", nativeQuery = true)
		CMG_MASTER getAccountDetailsCust(String CUSTID);
		
		
		@Query(value = "select distinct cust_id,cust_name,riskrating from CUST_MAST_GEN_TABLE ", nativeQuery = true)
		List<Object[]>  findAllCustom();
		
      /*********************************************SEARCH DETAILS*********************************************/		
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where cif_id LIKE %?1%", nativeQuery = true)
		List<CMG_MASTER> getcustomerId(String custid2);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where   UPPER(cust_name)  like UPPER(?1)", nativeQuery = true)
		List<CMG_MASTER> getcustomerName(String custName2);
		
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where upper(residing_country) like UPPER(?1)", nativeQuery = true)
		List<CMG_MASTER> getcustomerDOB(String custName2);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where preferredphone LIKE %?1%", nativeQuery = true)
		List<CMG_MASTER> getcustomerNUMBER(String custName2);
		
		@Query(value = "select count(*) from BAML_CUST_MAST_TABLE where preferredphone LIKE %?1%", nativeQuery = true)
		String getcustomerNUMBERcount(String custName2);
			
		@Query(value = "select * from BAML_CUST_MAST_TABLE where preferredemail LIKE %?1%", nativeQuery = true)
		List<CMG_MASTER> getcustomerEMAIL(String custName2);
		
		@Query(value = "select count(*) from BAML_CUST_MAST_TABLE where preferredemail LIKE %?1%", nativeQuery = true)
		String getcustomerEMAILcount(String custName2);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where nat_id_card_num LIKE %?1%", nativeQuery = true)
		List<CMG_MASTER> getcustomerNatID(String custName2);
		
		@Query(value = "select count(*) from BAML_CUST_MAST_TABLE where nat_id_card_num LIKE %?1%", nativeQuery = true)
		String getcustomerNatIDcount(String custName2);
		
		
		
//		888888*************************************************8report
		
		@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME " + 
				"from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t " + 
				"where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('LAA','CLA') " + 
				"and tran_date between ?1 and ?2 ", nativeQuery = true)
		List<Object[]> findAllCustIdfordailyLoanReport(Date startDate,Date endDate);
		
		@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME " + 
				"from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t " + 
				"where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('SBA','CAA','ODA') " + 
				"and tran_date between ?1 and ?2 ", nativeQuery = true)
		List<Object[]> findAllCustIdfordailyRSSReport(Date startDate,Date endDate);
		
		
		@Query(value = "select DISTINCT(c.cif_id),c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,c.CUST_FIRST_NAME " + 
				"from BAML_CUST_MAST_TABLE c  ,BAML_ACCT_MAST_TABLE a ,baml_acct_trans_table t " + 
				"where a.cust_id=c.cust_id and a.cust_id = t.cust_id and SCHM_TYPE IN ('TDA','TUA') " + 
				"and tran_date between ?1 and ?2 ", nativeQuery = true)
		List<Object[]> findAllCustIdfordailyDepositReport(Date startDate,Date endDate);
		
		
		@Query(value = "select c.cif_id,c.cust_id,c.cust_name,c.occupation,c.nat_id_card_num,c.UNIQUEID,t.TRAN_DATE,t.TRAN_AMT,c.CUST_FIRST_NAME from BAML_CUST_MAST_TABLE c ,BAML_ACCT_MAST_TABLE a left outer join baml_acct_trans_table t on a.acid=t.acid where a.cust_id=c.cust_id and TRAN_TYPE = 'C'  and TRAN_SUB_TYPE IN ('NR','NP') and tran_date between ?1 and ?2 order by TRAN_DATE DESC ", nativeQuery = true)
		List<Object[]> findAllCustIdfordailyCASHReport(Date startdate,Date endDate);
		
	
		
		//Dashboard Query//
		@Query(value = "select customer_count from BAML_DASHBOARD", nativeQuery = true)
		long findCustomcount();
		
		@Query(value = "select ACCOUNT_COUNT from BAML_DASHBOARD", nativeQuery = true)
		long findAcccount();
		
		@Query(value = "select TRANSACTION_COUNT from BAML_DASHBOARD", nativeQuery = true)
		long findTranCount();
		
		
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='43' and ref_code in (select cust_type_code from BAML_CUST_MAST_TABLE where nat_id_card_num=?1 and city_code=?2)", nativeQuery = true)
		CMG_MASTER getCity(String nid,String city_code);
		
		
		
		@Query(value = "select ref_desc from BAML_REFERENCE_CODE_TABLE where ref_rec_type ='01' and ref_code in (select placeofbirth from BAML_CUST_MAST_TABLE where  placeofbirth=?1)", nativeQuery = true)
		String getPOBDesc(String pob);
		
		
		@Query(value = "select a.cust_first_name,a.cust_last_name,a.cust_name,a.residing_country,a.address1,a.address2,b.ref_desc from BAML_CUST_MAST_TABLE a,baml_reference_code_table b where a.nat_id_card_num=and b.ref_rec_type ='01' and b.ref_code = a.placeofbirth", nativeQuery = true)
		List<Object> getNidFetchDet(String nid);
		
		@Query(value = "select count(*) from BAML_CUST_MAST_TABLE where nat_id_card_num=?1", nativeQuery = true)
		int getblackListBCM(String nid2);
		
		
		
		
		@Query(value = "select cust_name from BAML_CUST_MAST_TABLE where cust_first_name=?1 and cust_last_name=?2", nativeQuery = true)
		String findByFirstName(String custname,String custname1);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_middle_name=?1", nativeQuery = true)
		String findBySecondName(String custname);
		
		@Query(value = "select * from BAML_CUST_MAST_TABLE where cust_last_name=?1", nativeQuery = true)
		String findByThirdName(String custname);
}