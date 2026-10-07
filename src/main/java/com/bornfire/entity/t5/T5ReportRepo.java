package com.bornfire.entity.t5;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface T5ReportRepo extends JpaRepository<T5Report, Date>{
	

	@Query(value = "SELECT SUM((T5_9C_TOTAL_CUST_UNCHANGE_CUR_FACETOFACE)+(T5_10C_TOTAL_CUR_FACETOFACE)) as TOTAL FROM T5_RISK_RATING_MIG_SUMARY_TABLE WHERE T5_REPORT_TO_DATE=?1", nativeQuery = true)
	long findVal4(String report_date);

	
	@Query(value = "SELECT SUM((T5_10D_TOTAL_CUR_NON_FACETOFACE)+(T5_9D_TOTAL_CUST_UNCHANGE_CUR_NON_FACETOFACE)) as TOTAL FROM T5_RISK_RATING_MIG_SUMARY_TABLE WHERE T5_REPORT_TO_DATE=?1", nativeQuery = true)
	long findVal6(String report_date);
}
