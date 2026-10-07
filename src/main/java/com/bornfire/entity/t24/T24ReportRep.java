package com.bornfire.entity.t24;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface T24ReportRep extends JpaRepository<T24Report, Date> {
	
	@Query(value = "select * from T24_INT_ADT_AML_CFT_TABLE where REPORT_DATE=?1", nativeQuery = true)
	List<T24Report> gett24Report(Date d1);

}
