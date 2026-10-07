package com.bornfire.entity.t13;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t12.T12Detail;




@Repository
public interface T13DetailRepo  extends JpaRepository<T13Detail, T13DetailId> {
	
	 @Query(value = "select * from T13_IND_CASH_DEP_DETAILS a where report_date = ?1", nativeQuery = true)
		Page<T13Detail> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T12_IND_CASH_DEP_DETILS where report_date=?1", nativeQuery = true)
		List<T13Detail> getReportList(String report_date );

}
