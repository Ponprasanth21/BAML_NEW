package com.bornfire.entity;

import java.math.BigDecimal;
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
public interface TransactionMasterRepository extends JpaRepository<TransactionMaster,String> {
	 Optional<TransactionMaster> findById( String directorId); 
	 
	 @Query(value="select * from DTD where tran_id=?1",nativeQuery = true)
		Page<TransactionMaster> findAlltranIdCustom(String tranId,Pageable pageable);
	 
	 @Query(value = "select * from DTD where TRAN_AMT between ?1 and ?2", nativeQuery = true)
		Page<TransactionMaster> findAllCustomList(Pageable page, BigDecimal value_min, BigDecimal value_max);
	 
	 @Query(value = "select value_date from DTD TRAN_AMT between ?1 and ?2", nativeQuery = true)
		Date getDate(String id);
	 @Query(value = "select * from DTD where cust_id=?1", nativeQuery = true)
	 TransactionMaster getAccountDetailsCust(String CUSTID);  
	 
	 @Query(value = "select * from DTD where cust_id=?1 and tran_date between ?2 and ?3 ", nativeQuery = true)
	 TransactionMaster getAccountDetailsCustdate(String customer,String fromdate,String todate); 
	 
	 @Query(value = "select count(*) from DTD where cust_id=?1", nativeQuery = true)
	 String TranCount(String CUSTID); 
}