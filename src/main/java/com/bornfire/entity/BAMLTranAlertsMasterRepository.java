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
	
	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where (upper(to_char(\"TRAN_DATE\", 'DD-Mon-YYYY')) = upper(?1) or to_char(\"TRAN_DATE\", 'DD-MM-YYYY') = ?1 or to_char(\"TRAN_DATE\", 'DD/MM/YYYY') = ?1 or to_char(\"TRAN_DATE\", 'YYYY-MM-DD') = ?1) and (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null)", nativeQuery = true) 
	 Page<BAMLTranAlertsMaster> parameterlist(String Fromdate,Pageable page);

	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null) and \"TRAN_DATE\" between ?1 and ?2 order by \"TRAN_DATE\" desc", nativeQuery = true) 
	 Page<BAMLTranAlertsMaster> parameterlistdate(Date Fromdate,Date Todate,Pageable page);

	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where cast(\"AML_TRAN_REF_NO\" as varchar)=?1 or \"TRAN_ID\"=?1", nativeQuery = true) 
	 BAMLTranAlertsMaster findByIdcustom(String tranid);
	 
	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where cast(\"AML_TRAN_REF_NO\" as varchar)=?1 or \"TRAN_ID\"=?1", nativeQuery = true) 
	 Optional<BAMLTranAlertsMaster> findByIdcustomResult(String tranid);
	 
	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where \"TRAN_DATE\" = (select max(\"TRAN_DATE\") from \"AML\".\"BAML_TRAN_ALERTS_MASTER\") and (\"CUST_ID\" like concat('%', ?1, '%') OR \"ACCT_NAME\" like concat('%', ?1, '%') OR \"RULE_CODE\" like concat('%', ?1, '%')) ", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistwithoudate(String freeText,Pageable page);
	 
	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where \"TRAN_DATE\" between ?1 and ?2 order by \"TRAN_DATE\" desc", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistwithdate(Date fromdate, Date todate, Pageable page);
	
	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where \"TRAN_DATE\" between ?1 and ?2 and ( \"CUST_ID\" like concat('%', ?3, '%') OR \"ACCT_NAME\" like concat('%', ?3, '%') OR \"RULE_CODE\" like concat('%', ?3, '%') ) order by \"TRAN_DATE\" desc", nativeQuery = true)
	 Page<BAMLTranAlertsMaster> parameterlistSearch(Date fromdate, Date todate,String FreeText, Pageable page);

	 @Query(value = "select * from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null) order by \"TRAN_DATE\" desc", nativeQuery = true) 
	 Page<BAMLTranAlertsMaster> findAllAlerts(Pageable page);

	 @Query(value = "select max(\"TRAN_DATE\") from \"AML\".\"BAML_TRAN_ALERTS_MASTER\" where (trim(\"DEL_FLG\")='N' or \"DEL_FLG\" is null)", nativeQuery = true) 
	 Date findLatestTranDate();
}
