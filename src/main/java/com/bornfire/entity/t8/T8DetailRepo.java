package com.bornfire.entity.t8;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t9.T9Detail;


@Repository
public interface T8DetailRepo extends JpaRepository<T8Detail, Date> {
	
	 @Query(value = "select * from T8_TRAN_CUST_TYPE_DETAIL  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T8Detail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T8_TRAN_CUST_TYPE_DETAIL  where report_date = ?1", nativeQuery = true)
		Page<T8Detail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1", nativeQuery = true)
	 Page<T8Detail> getReportList(String report_date,Pageable page);
	 
	 @Query(value = "select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T8Detail getview(String d1, String cif_id);
}
