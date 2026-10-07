package com.bornfire.entity.t15;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t18.T18Detail;


@Repository
public interface T15DetailRepo extends JpaRepository<T15Detail, String> {
	
	 @Query(value = "select * from T15_CHQ_OUT_DETAILS a where report_date = ?1", nativeQuery = true)
		Page<T15Detail> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T15_CHQ_OUT_DETAILS where report_date=?1", nativeQuery = true)
	 Page<T15Detail> getReportList(String report_date,Pageable page);
	 
	 @Query(value = "select * from T15_CHQ_OUT_DETAILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T15Detail getview(String d1, String cif_id);

}
