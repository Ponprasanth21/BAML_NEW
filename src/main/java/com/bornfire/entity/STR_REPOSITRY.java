package com.bornfire.entity;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface STR_REPOSITRY extends JpaRepository<AML_STR_ENTITY, String> {

	@Query(value = "SELECT * FROM \"AML\".\"BAML_STR_TABLE\" ORDER BY \"REPORT_ID\" DESC", countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_STR_TABLE\"", nativeQuery = true)
	Page<AML_STR_ENTITY> reportlist(Pageable page);
	
	@Query(value = "select * from \"AML\".\"BAML_STR_TABLE\" where substring(\"TABLE_KEY\", 1, ?2) = ?1", nativeQuery = true)
	AML_STR_ENTITY getCustAuditlist(Date tran_date);
	
}
