package com.bornfire.entity.t13;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T13ReportRepo extends JpaRepository <T13Report,Date> {
	Optional<T13Report> findById(Date directorId);
	
	@Query(value = "SELECT SUM((C8B_CUR_TOTAL_NOF_WDL_TRAN_HIG)+(C8D_CUR_TOTAL_NOF_WDL_TRAN_MED)+(C8F_CUR_TOTAL_NOF_WDL_TRAN_LOW))AS TOTAL FROM T13_IND_CASH_WDL_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal15(String report_date);

	

	@Query(value = "SELECT SUM((C8C_CUR_TOTAL_TO_VAL_TRAN_HIG)+(C8E_CUR_TOTAL_TO_VAL_TRAN_MED)+(C8G_CUR_TOTAL_TO_VAL_TRAN_LOW))AS TOTAL FROM T13_IND_CASH_WDL_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal17(String report_date);

}
