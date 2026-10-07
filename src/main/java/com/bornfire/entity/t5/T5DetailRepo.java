package com.bornfire.entity.t5;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t8.T8Detail;


@Repository
public interface T5DetailRepo extends JpaRepository<T5Detail, Date> {
	
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T5Detail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date = ?1", nativeQuery = true)
		Page<T5Detail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE where report_date=?1", nativeQuery = true)
	 Page<T5Detail> getReportList(String report_date,Pageable page);

	 
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T5Detail getview(String d1, String cif_id);
}
