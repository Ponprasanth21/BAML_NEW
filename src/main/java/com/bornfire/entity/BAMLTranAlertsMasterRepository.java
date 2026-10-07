package com.bornfire.entity;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAMLTranAlertsMasterRepository  extends JpaRepository<BAMLTranAlertsMaster, AlertTransactionEmbedded> {
	
	 @Query(value = "select * from BAML_TRAN_ALERTS_MASTER where tran_date =?1 and DEL_FLG='N'  ", nativeQuery = true) 
	 Page<BAMLTranAlertsMaster> parameterlist(String Fromdate,Pageable page);

	 @Query(value = "select * from BAML_TRAN_ALERTS_MASTER where DEL_FLG='N' and tran_date between ?1 and ?2 order by  tran_date ", nativeQuery = true) 
	 Page<BAMLTranAlertsMaster> parameterlistdate(Date Fromdate,Date Todate,Pageable page);

	 @Query(value = "select * from BAML_TRAN_ALERTS_MASTER where aml_tran_ref_no=?1 ", nativeQuery = true) 
	BAMLTranAlertsMaster findByIdcustom(String tranid);
	 
	 @Query(value = "select * from BAML_TRAN_ALERTS_MASTER where aml_tran_ref_no=?1  ", nativeQuery = true) 
	 Optional<BAMLTranAlertsMaster> findByIdcustomResult(String tranid);
	 
	 @Query(value = "select * from baml_tran_alerts_master where tran_date = (select max(tran_date) from baml_tran_alerts_master) and (cust_id like %?1% OR acct_name like %?1% OR rule_code like %?1%) ", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistwithoudate(String freeText,Pageable page);
	 
	 
	 @Query(value = "select * from baml_tran_alerts_master where tran_date between ?1 and ?2  ", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistwithdate(Date fromdate, Date todate, Pageable page);
//and ( cust_id like %?3% OR acct_name like %?3% OR rule_code like %?3% )
	
	 @Query(value = "select * from baml_tran_alerts_master where tran_date between ?1 and ?2 and ( cust_id like %?3% OR acct_name like %?3% OR rule_code like %?3% ) ", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistSearch(Date fromdate, Date todate,String FreeText, Pageable page);

	 
}
