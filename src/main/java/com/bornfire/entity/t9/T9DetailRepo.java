package com.bornfire.entity.t9;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t10.T10Detail;
import com.bornfire.entity.t8.T8Detail;


@Repository
public interface T9DetailRepo  extends JpaRepository<T9Detail, String> {
	
	 @Query(value = "select * from T9_DOM_OW_REMIT_DETAILS  where report_date =?1 and cell_mapping =?2", nativeQuery = true)
		Page<T9Detail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T9_DOM_OW_REMIT_DETAILS  where report_date = ?1", nativeQuery = true)
		Page<T9Detail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T9_DOM_OW_REMIT_DETAILS where report_date=?1", nativeQuery = true)
	 Page<T9Detail> getReportList(String report_date,Pageable page);
	 
	 @Query(value = "select * from T9_DOM_OW_REMIT_DETAILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T9Detail getview(String d1, String cif_id);
}
