package com.bornfire.entity;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAML_AUDIT_REPOSITRY extends JpaRepository<BAML_AUDIT_ENTITY, String> {

	@Query(value = "select distinct * from BAML_AUDIT_TABLE_FINACLE where foracid=?1 and modified_value is not null", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getauditlist(String foracid,Pageable pageable);

	
	
	@Query(value = "select  * from BAML_AUDIT_TABLE_FINACLE where foracid like %?1% and modified_value is not null", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getCustAuditlist(String cust_id,int acct_len,Pageable pageable);
	

	@Query(value = "select * from BAML_AUDIT_TABLE_FINACLE where audit_date between ?1 and ?2 and modified_value like %?3%", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getMonitoringList(Date fromdate,Date todate,String ruletype,Pageable pageable);
	
	@Query(value ="SELECT distinct v_title AS TITLE,auditedid AS AUDITEDID,crm.orgkey AS ORGKEY,crm.cust_last_name || ' ' || crm.cust_first_name V_NAMES,usr.loginid AS ENTRY_USER,v_loginid AS VERIFIED_USER,trunc(aud.bodatecreated) bodatecreated ,trunc(aud.bodatemodified) bodatemodified,Replace(SUBSTR(REGEXP_REPLACE(substr(aud.v_change_values,1,INSTR(aud.v_change_values, '>')),'[^0-9A-Za-z]',' '),INSTR(REGEXP_REPLACE(substr(aud.v_change_values,1,INSTR(aud.v_change_values,'>')),'[^0-9A-Za-z]',' '),'v ',1,1)+3,3),'o','') NEW_risk_type ,REPLACE(SUBSTR(REGEXP_REPLACE(substr(aud.v_change_values,1,INSTR(aud.v_change_values, '>')),'[^0-9A-Za-z]',' '),INSTR(REGEXP_REPLACE(substr(aud.v_change_values,1,INSTR(aud.v_change_values,'>')),'[^0-9A-Za-z]',' '),'ov ',1,1)+4,3),'eCo','') old_risk_type FROM (select 'Department' v_title,a.auditedid,a.bocreatedby,a.bomodifiedby,a.bodatecreated,a.bodatemodified,to_char(substr(a.changedvalue,dbms_lob.instr(A.CHANGEDVALUE,?3),35)) v_change_values,TRIM(substr(to_char(substr(a.changedvalue,dbms_lob.instr(A.CHANGEDVALUE,?3),35)),21,2)) v_values,dbms_lob.instr(a.changedvalue, 'FreeCode2Desc') from BAML_audittrail a where trunc(a.bodatecreated) BETWEEN ?1 AND ?2 and dbms_lob.instr(A.CHANGEDVALUE, 'FreeCode2Desc') >= 1 ) Aud,crmuser.accounts@AML_FIN crm,crmuser.users@AML_FIN usr,(select vusr.personid v_userid, vusr.loginid v_loginid from crmuser.users@AML_FIN vusr) where  v_values <> ''''''  and aud.bocreatedby <> aud.bomodifiedby and aud.auditedid = crm.accountid and aud.bocreatedby = usr.personid and aud.bomodifiedby = v_userid", nativeQuery = true)
	List<Object[]> getMonitoringListaudit(Date fromdate,Date todate,String ruletype,Pageable pageable);
	
	
	@Query(value = "select * from BAML_AUDIT_TABLE_FINACLE where audit_date between ?1 and ?2 and modified_value like %?3%", nativeQuery = true)
	Page<BAML_AUDIT_ENTITY> getMonitoringList1(String fromdate,String todate,Pageable pageable);

	@Query(value = "select value1 from BAML_MON_ALERT_PARAM where table_field =?1 ", nativeQuery = true)
	String getruledata(String rule);
	
	

}
