package com.bornfire.entity.t23;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T23ReportRepo extends JpaRepository<T23Report, Date> {
	
	@Query(value = "select * from T23_AML_CFT_REVIEWS_TABLE where REPORT_DATE=?1", nativeQuery = true)
	List<T23Report> gett23Report(Date d1);
	

}
