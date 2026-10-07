package com.bornfire.entity.t4;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T4DetailRepo extends JpaRepository<T4ReportDetail, Date> {
	
	 @Query(value = "select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T4ReportDetail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE  where report_date = ?1", nativeQuery = true)
		Page<T4ReportDetail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE where report_date=?1", nativeQuery = true)
	 Page<T4ReportDetail> getReportList(String report_date ,Pageable page);

}
