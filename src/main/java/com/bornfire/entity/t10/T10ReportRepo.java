package com.bornfire.entity.t10;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface T10ReportRepo extends JpaRepository<T10Report, Date> {
	Optional<T10Report> findById(Date directorId);

	@Query(value = "SELECT SUM((C20B_CUR_TOTAL_NOT_LOW)+(C20D_CUR_TOTAL_NOT_MED)+(C20F_CUR_TOTAL_NOT_HIG))AS TOTAL FROM T10_DOM_INW_REMIT_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal21(String report_date);

	@Query(value = "SELECT SUM((C20C_CUR_TOTAL_TAMT_LOW)+(C20E_CUR_TOTAL_TAMT_MED)+(C20G_CUR_TOTAL_TAMT_HIG))AS TOTAL FROM T10_DOM_INW_REMIT_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal22(String report_date);
}
