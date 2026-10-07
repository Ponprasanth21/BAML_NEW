package com.bornfire.entity.t3a;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t1.T1CurProdServices;


@Repository
public interface T3ADetailRepo extends JpaRepository<T3ADetail, String> {
	
	 @Query(value = "select * from T3A_PROFILE_FACE_TO_FACE_DET_TB  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
		Page<T3ADetail> detailList(Date todate,String filter,Pageable page);
	 
	 @Query(value = "select * from T3A_PROFILE_FACE_TO_FACE_DET_TB  where report_date = ?1", nativeQuery = true)
		Page<T3ADetail> detailList1(Date todate,Pageable page);
	 
	 @Query(value = "select * from T3A_PROFILE_FACE_TO_FACE_DET_TB where report_date=?1", nativeQuery = true)
	 Page<T3ADetail> getReportList(String report_date,Pageable page );
}
