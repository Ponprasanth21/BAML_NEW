package com.bornfire.entity.t12;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t10.T10Detail;
import com.bornfire.entity.t14.T14Detail;


@Repository
public interface T12DetailRepo extends JpaRepository<T12Detail, Date> {
	
	 @Query(value = "select * from T12_IND_CASH_DEP_DETILS  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T12Detail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T12_IND_CASH_DEP_DETILS  where report_date = ?1", nativeQuery = true)
		Page<T12Detail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T12_IND_CASH_DEP_DETILS where report_date=?1", nativeQuery = true)
	 Page<T12Detail> getReportList(String report_date,Pageable page);
	 
	 
	 @Query(value = "select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T12Detail getview(String d1, String cif_id);
}
