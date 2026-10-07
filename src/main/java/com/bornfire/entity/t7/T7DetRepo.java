package com.bornfire.entity.t7;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bornfire.entity.t5.T5Detail;

public interface T7DetRepo extends JpaRepository<T7Detail, String> {
	
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T5Detail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date = ?1", nativeQuery = true)
		Page<T5Detail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T5_RISK_RATING_MIG_DETAILED_TABLE where report_date=?1", nativeQuery = true)
		List<T5Detail> getReportList(String report_date );
}
