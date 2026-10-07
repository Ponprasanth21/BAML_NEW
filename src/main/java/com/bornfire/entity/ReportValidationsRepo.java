package com.bornfire.entity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ReportValidationsRepo extends JpaRepository<ReportValidations, String> {

	Optional<ReportValidations> findById(String directorId);

	@Query(value = "select * from BAML_REPORT_VALIDATION_TABLE ORDER BY srl_no", nativeQuery = true)
	List<ReportValidations> getValidationList();
	
	@Query(value = "select * from BAML_RPT_MAST ORDER  BY SRL_NO", nativeQuery = true)
	List<RBSReport> getReport_details();

	@Query(value = "select DISTINCT to_char(CUR_QTR_END_DATE,'dd/MM/yyyy')as CUR_QTR_END_DATE from BAML_RPT_MAST", nativeQuery = true)
	String getCurrentQtr(SimpleDateFormat simpleDateFormat);



	
}
