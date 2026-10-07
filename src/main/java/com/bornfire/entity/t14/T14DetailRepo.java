package com.bornfire.entity.t14;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t15.T15Detail;


@Repository
public interface T14DetailRepo extends JpaRepository<T14Detail, String> {
	
	 @Query(value = "select * from T14_CHQ_INW_DETAILS a where report_date = ?1", nativeQuery = true)
		Page<T14Detail> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T14_CHQ_INW_DETAILS where report_date=?1", nativeQuery = true)
	 Page<T14Detail> getReportList(String report_date ,Pageable page);

	 @Query(value = "select * from T14_CHQ_INW_DETAILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T14Detail getview(String d1, String cif_id);
	 
}
