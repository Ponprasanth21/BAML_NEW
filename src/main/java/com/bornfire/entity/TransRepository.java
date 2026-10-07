package com.bornfire.entity;

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
	public interface TransRepository extends JpaRepository<TRAN_MASTER,String> {
		 Optional<TRAN_MASTER> findById( String directorId);
		 
		 @Query(value="select * from BAML_ACCT_TRANS_TABLE ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC",nativeQuery = true)
			Page<TRAN_MASTER> findAllCustom(Pageable pageable);
		 
		 
		 @Query(value="select to_char(a.TRAN_DATE,'dd-mm-yyyy')tran_date,b.FORACID,b.ACCT_NAME,a.PART_TRAN_TYPE,a.TRAN_ID,a.PART_TRAN_SRL_NUM,b.CUST_ID,a.TRAN_CRNCY_CODE,a.TRAN_AMT,a.TRAN_PARTICULAR,a.MODULE_ID,a.acid,b.cif_id from BAML_ACCT_TRANS_TABLE a,BAML_ACCT_MAST_TABLE b where b.ACID = a.ACID AND a.TRAN_ID =?1 AND a.tran_date = ?2 order by part_tran_srl_num  ",nativeQuery = true)
		 List<Object[]> getTransactionDetails(String tranid, String parttranid,String trandate);
		
		 @Query(value="select to_char(a.TRAN_DATE,'dd-mm-yyyy')tran_date,b.FORACID,b.ACCT_NAME,a.PART_TRAN_TYPE,a.TRAN_ID,a.PART_TRAN_SRL_NUM,b.CUST_ID,a.TRAN_CRNCY_CODE,a.TRAN_AMT,a.TRAN_PARTICULAR,a.MODULE_ID,a.acid,b.cif_id from BAML_ACCT_TRANS_TABLE a,BAML_ACCT_MAST_TABLE b where b.ACID = a.ACID AND a.TRAN_ID =?1 AND a.tran_date =?2 order by part_tran_srl_num  ",nativeQuery = true)
		 List<Object[]> getTransactionDate(String tranid, String tranDate);
		 
		 @Query(value="select * from BAML_ACCT_TRANS_TABLE where acid =?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC  ",nativeQuery = true)
			Page<TRAN_MASTER> getTransaction(String acid,Pageable pageable);
		 
		 @Query(value="select count(*) from BAML_ACCT_TRANS_TABLE where acid =?1 ",nativeQuery = true)
			String getTransactioncount(String acid);
		 
		 
		 	
	}

