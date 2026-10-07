package com.bornfire.entity;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t5.T5Detail;


@Repository
@Transactional
public interface T2CurrentDetailRepo extends JpaRepository<T2CurrentMast, String> {
	
	 @Query(value = "select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date = ?1", nativeQuery = true)
		Page<T2CurrentMast> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1", nativeQuery = true)
	    Page<T2CurrentMast> getReportList(String report_date,Pageable page);

	 @Query(value = "select * from T2_CFT_CUSTOMER_RATING_MAST_TB where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T2CurrentMast getview(String d1, String cif_id);
}
