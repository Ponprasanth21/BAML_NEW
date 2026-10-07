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
public interface ThirdPartyRepository extends JpaRepository<BAMLThirdPartyTran, String> {
	
	Optional<BAMLThirdPartyTran> findById(String directorId);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where trunc(payment_date)=?1", nativeQuery = true)
	Page<BAMLThirdPartyTran> getThirdPartylist(String Fromdate, Pageable pageable);
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where entity_flg='Y' and trunc(payment_date)=?1", nativeQuery = true)
	Page<BAMLThirdPartyTran> getThirdPartylistRep(String Fromdate, Pageable pageable);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where trunc(payment_date) between ?1 and ?2 ", nativeQuery = true)
	Page<BAMLThirdPartyTran> getThirdPartylistFilter(Date Fromdate, Date Todate, Pageable pageable);
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where entity_flg='Y' and trunc(payment_date) between ?1 and ?2 ", nativeQuery = true)
	Page<BAMLThirdPartyTran> getThirdPartylistFilterRep(Date Fromdate, Date Todate, Pageable pageable);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where srl_no=?1 ", nativeQuery = true)
	BAMLThirdPartyTran findByIdcustom(String srlNo);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE", nativeQuery = true)
	List<BAMLThirdPartyTran> findAllCustom();

	@Query(value = "select nvl(sum(amount_paid),0)as count from BAML_THIRD_PARTY_TRAN_TABLE where nid=?1 and trunc(payment_date)=?2 and mode_of_payment=?3", nativeQuery = true)
	String findAmt(String nid, String paydate, String paymode);

	@Query(value = "select nvl(sum(amount_paid),0)as count from BAML_THIRD_PARTY_TRAN_TABLE where trunc(payment_date) between ?1 and ?2 and nid=?3 and mode_of_payment=?4", nativeQuery = true)
	String findPrevAmt(Date fromDate, Date toDate, String NID, String paymode);

	@Query(value = "select count(*) from BAML_THIRD_PARTY_TRAN_TABLE where cust_id=?1 and payment_flg='Y' ", nativeQuery = true)
	long findThirdPartyCount(String custId);

	@Query(value = "select param_value_1 from BAML_ALERT_MGMT_TABLE where alert_code='THIRD-PARTY-AMT'", nativeQuery = true)
	long findNormalLimit();

	@Query(value = "select count(*) from BAML_THIRD_PARTY_TRAN_TABLE where tran_id=?1 and part_tran_type=?2 and trunc(payment_date)=?3", nativeQuery = true)
	int findPreTran(String tran_id,String part_tran_type, Date tran_date);
	
	
	@Query(value = "select param_value_1 from BAML_ALERT_MGMT_TABLE where alert_code='THIRD-PARTY-AMT-SELF'", nativeQuery = true)
	long findNormalLimitself();
	
	
	@Query(value = "select param_value_2 from BAML_ALERT_MGMT_TABLE where alert_code='THIRD-PARTY-AMT'", nativeQuery = true)
	long findCreditLimitPerDay();

	@Query(value = "select param_value_3 from BAML_ALERT_MGMT_TABLE where alert_code='THIRD-PARTY-AMT'", nativeQuery = true)
	long findCreditLimitPerLast14Day();
	
	
	@Query(value = "select IDTYPER1  from DEDUPADM.BLACKLISTDATASET@AML_FIN where IDTYPER1=?1", nativeQuery = true)
	String findNIDBlackList(String nID);
	
	
	
	
	@Query(value = "select count(*)  from BAML_CUST_PEP_LIST  where cust_name=?1 and cust_short_name=?2", nativeQuery = true)
	int findNamePEPList(String cust_name,String cust_short_name);
	
	
	@Query(value = "select count(*)  from BAML_CUST_PEP_LIST  where cust_name=?1 ", nativeQuery = true)
	int findNamePEPListrest(String cust_name);
	
	@Query(value = "select count(*)  from BAML_UNSC_INDIVIDUAL_TABLE  where first_name=?1 and third_name=?2 ", nativeQuery = true)
	int findNameUNSCINDList(String Firstname,String Lastname);
	
	@Query(value = "select count(*)  from BAML_UNSC_ENTITY_TABLE  where first_name=?1", nativeQuery = true)
	int findNameUNSCENTList(String cust_name);
	
	@Query(value = "select count(*)  from BAML_UNSC_INDIVIDUAL_TABLE  where first_name=?1", nativeQuery = true)
	int findNameUNSCListrest(String Firstname);
	
	
	@Query(value = "select count(*)  from DEDUPADM.BLACKLISTDATASET@AML_FIN  where IDTYPER1=?1", nativeQuery = true)
	int findNIDfinrest(String nid2);
	
	
	
	@Query(value = "select count(*)  from BAML_CUST_PEP_LIST  where cust_name=?1", nativeQuery = true)
	int findNamefinrest(String NAME);
	
	
	
	
	@Query(value = "select count(*)  from BAML_UNSC_INDIVIDUAL_TABLE  where first_name=?1 and third_name=?2", nativeQuery = true)
	int findNameUNSCfinrest(String NAME1,String NAME2);
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where cust_id LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustID(Pageable pageable, String custId, Date Fromdate, Date Todate);
	
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where entity_flg='Y' and cust_id LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustIDRep(Pageable pageable, String custId, Date Fromdate, Date Todate);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where cust_full_name LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustName(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where entity_flg='Y' and cust_full_name LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustNameRep(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where nid LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustNID(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where  entity_flg='Y' and nid LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustNIDRep(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);
	
	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where risk_category LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustRisk(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);

	@Query(value = "select * from BAML_THIRD_PARTY_TRAN_TABLE where  entity_flg='Y' and  risk_category LIKE %?1% and trunc(payment_date) between ?2 and ?3", nativeQuery = true)
	Page<BAMLThirdPartyTran> getlistBycustRiskRep(Pageable pageable, String cust_full_name, Date Fromdate, Date Todate);

}
