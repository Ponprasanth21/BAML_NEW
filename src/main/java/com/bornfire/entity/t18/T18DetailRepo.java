package com.bornfire.entity.t18;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t1.T1CurProdServices;
import com.bornfire.entity.t15.T15Detail;


@Repository
public interface T18DetailRepo extends JpaRepository<T18Detail, String> {
	
	 @Query(value = "select * from T18DIST_CHANNELS_DETAILS a where report_date = ?1", nativeQuery = true)
		Page<T18Detail> detailList(Date todate,Pageable page);
	 
	 @Query(value = "select * from T18DIST_CHANNELS_DETAILS where report_date=?1", nativeQuery = true)
	 Page<T18Detail> getReportList(String report_date,Pageable page);
	 
	 @Query(value = "select * from T18DIST_CHANNELS_DETAILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	 T18Detail getview(String d1, String cif_id);

}
