package com.bornfire.entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface T2CurrentRptRepo extends JpaRepository<T2CurrentRpt, Date> {

	Optional<T2CurrentRpt> findById(Date directorId);
	
	
	@Query(value = "select INTERNAL_RATING_FACE_TO_FACE_3B from T2_CFT_CUSTOMER_RATING_RPT_TB where report_date=?1", nativeQuery = true)
	long findVal1low(String report_date);
	
	
	@Query(value = "select INTERNAL_RATING_FACE_TO_FACE_2B from T2_CFT_CUSTOMER_RATING_RPT_TB where report_date=?1", nativeQuery = true)
	long findVal1medium(String report_date);
	
	
	@Query(value = "select INTERNAL_RATING_FACE_TO_FACE_1B from T2_CFT_CUSTOMER_RATING_RPT_TB where report_date=?1", nativeQuery = true)
	long findVal1high(String report_date);

}
