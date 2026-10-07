package com.bornfire.entity.T26;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface T26ReportRepo extends JpaRepository<T26Report, Date> {
	
	@Query(value = "select * from T26_AML_ISS_TABLE where REPORT_DATE=?1", nativeQuery = true)
	List<T26Report> getT26ReportSummary(Date d1);
	

}
