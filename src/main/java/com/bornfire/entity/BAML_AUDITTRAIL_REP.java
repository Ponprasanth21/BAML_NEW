package com.bornfire.entity;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BAML_AUDITTRAIL_REP extends JpaRepository<BAML_AUDIT_TRAIL_OUT, String> {

	@Query(value ="SELECT * FROM BAML_AUDIT_LOCAL WHERE ENTRY_DATE BETWEEN ?1 AND ?2 AND TITLE=?3", nativeQuery = true)
	Page<BAML_AUDIT_TRAIL_OUT> getMonitoringListaudit(Date fromdate,Date todate,String ruletype,Pageable pageable);
	
}
