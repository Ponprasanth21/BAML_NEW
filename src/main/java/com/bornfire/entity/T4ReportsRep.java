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

	
	 @Query(value = "select * from BAML_CUST_APPL_REJ order by SRL_NO", nativeQuery = true)
		Page<T4Report> rejectList(Pageable page);
	 
	 @Query(value = "select ref_desc from baml_reference_code_table where ref_rec_type ='T4' and ref_rec_desc = 'CUSTOMER TYPE' ", nativeQuery = true) 
	 List<String> parameterDropdown();
	 
	 @Query(value = "select distinct(tran_sub_type) from baml_acct_trans_table", nativeQuery = true) 
	 List<String> dropDown1();
	 
	 
	 @Query(value = "select to_char(CUR_QTR_END_DATE,'dd/MM/yyyy')as report_date from BAML_RPT_MAST  where ROWNUM=1", nativeQuery = true) 
	 String curqtrDate();
	 
	 
	 @Query(value = " SELECT to_char(report_date,'dd/MM/yyyy')as report_date FROM t4_customer_profiling_summary_table WHERE ROWNUM=1",nativeQuery = true)
	 String date();

}