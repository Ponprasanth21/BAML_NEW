package com.bornfire.entity;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T4ReportsRep extends JpaRepository<T4Report, String>{

	Optional<T4Report> findById(String directorId);

	
	 @Query(value = "select * from \"AML\".\"BAML_CUST_APPL_REJ\" order by \"SRL_NO\"", countQuery = "select count(*) from \"AML\".\"BAML_CUST_APPL_REJ\"", nativeQuery = true)
		Page<T4Report> rejectList(Pageable page);
	 
	 @Query(value = "select \"REF_DESC\" from \"AML\".\"BAML_REFERENCE_CODE_TABLE\" where \"REF_REC_TYPE\" ='T4' and \"REF_REC_DESC\" = 'CUSTOMER TYPE'", nativeQuery = true) 
	 List<String> parameterDropdown();
	 
	 @Query(value = "select distinct(\"TRAN_SUB_TYPE\") from \"AML\".\"BAML_ACCT_TRANS_TABLE\"", nativeQuery = true) 
	 List<String> dropDown1();
	 
	 
	 @Query(value = "select to_char(\"CUR_QTR_END_DATE\",'dd/MM/yyyy') as report_date from \"AML\".\"BAML_RPT_MAST\" limit 1", nativeQuery = true) 
	 String curqtrDate();
	 
	 
	 @Query(value = "SELECT to_char(\"REPORT_DATE\",'dd/MM/yyyy') as report_date FROM \"AML\".\"T4_CUSTOMER_PROFILING_SUMMARY_TABLE\" limit 1", nativeQuery = true)
	 String date();

}