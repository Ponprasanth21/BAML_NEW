
package com.bornfire.entity;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AML_AUDIT_LOCAL_REP extends JpaRepository<AML_AUDIT_LOCAL, String> {

	@Query(value = "select * from BAML_AUDIT_TABLE_FINACLE where foracid=?1 ", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getauditlist(String foracid,Pageable pageable);

	
	
	@Query(value = "select * from BAML_AUDIT_TABLE_FINACLE where substr(table_key,1,?2)=?1 ", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getCustAuditlist(String cust_id,String acct_len,Pageable pageable);
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE where event_id=?1 and remarks in ('ADDED','MODIFIED') and audit_table ='BAML_USER_PROFILE_TABLE' ", nativeQuery = true)
	AML_AUDIT_LOCAL getAuditVerifyUser(String userid);
	
	

	@Query(value = "select * from BAML_AUDIT_TABLE where event_id=?1 and remarks in ('ADDED','MODIFIED') and audit_table ='BAML_RULE_ENGINE_TABLE' ", nativeQuery = true)
	AML_AUDIT_LOCAL getAuditVerifyMONITORING(String userid);
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE where event_name like %?1% and remarks in ('ADDED','MODIFIED') and audit_table ='THIRD_PARTY_TRAN_TABLE' ", nativeQuery = true)
	AML_AUDIT_LOCAL getAuditVerifyUserthirdparty(String userid);
	
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE where event_id=?1 and remarks in ('ADDED','MODIFIED','DELETED') and audit_table ='BAML_CUST_PEP_LIST' ", nativeQuery = true)
	AML_AUDIT_LOCAL getAuditVerifyUserPEP(String userid);
	
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE where event_id=?1 and remarks in ('ADDED','MODIFIED') and audit_table ='BAML_ACCESS_ROLE_TABLE' ", nativeQuery = true)
	AML_AUDIT_LOCAL getAuditVerifyUseraccess(String userid);
	
	@Query(value = "select * from BAML_AUDIT_TABLE where trunc(audit_date) between ?1 and ?2  AND audit_table  in ('USER PROFILE MAINTENANCE','BAML_USER_PROFILE_TABLE') and modi_details is not null order by audit_date", nativeQuery = true)
	Page<AML_AUDIT_LOCAL> getauditListLocal(Date Fromdate,Date Todate,Pageable pageable);
	
	@Query(value = "select * from BAML_AUDIT_TABLE where trunc(audit_date)=?1  AND audit_table  in ('USER PROFILE MAINTENANCE','BAML_USER_PROFILE_TABLE') and modi_details is not null", nativeQuery = true)
	Page<AML_AUDIT_LOCAL> getauditListLocal1(String Fromdate,Pageable pageable);
	
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE where trunc(audit_date) between ?1 and ?2  AND audit_table not in ('USER PROFILE MAINTENANCE','BAML_USER_PROFILE_TABLE')", nativeQuery = true)
	Page<AML_AUDIT_LOCAL> getauditListOpeartion(Date Fromdate,Date Todate,Pageable pageable);
	
	@Query(value = "select * from BAML_AUDIT_TABLE where trunc(audit_date) =?1  AND audit_table not in ('USER PROFILE MAINTENANCE','BAML_USER_PROFILE_TABLE')", nativeQuery = true)
	Page<AML_AUDIT_LOCAL> getauditListOpeartionsingle(Date Fromdate,Pageable pageable);
}
