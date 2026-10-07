package com.bornfire.entity.t21;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t18.T18Detail;


@Repository
public interface T21DetailRepo extends JpaRepository<T21Detail, String> {
	
	 @Query(value = "select * from T21_INACTIVE_DORM_ACCTS_DETAILS a where report_date = ?1", nativeQuery = true)
		Page<T21Detail> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T21_INACTIVE_DORM_ACCTS_DETAILS where report_date=?1", nativeQuery = true)
	 Page<T21Detail> getReportList(String report_date,Pageable page);

}
