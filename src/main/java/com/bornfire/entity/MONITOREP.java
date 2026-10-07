package com.bornfire.entity;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MONITOREP extends JpaRepository<MONITORINGPARAMETERENTIRY, String> {

	@Query(value = "select * from BAML_MON_ALERT_PARAM where del_flg='N' ", nativeQuery = true)
	Page<MONITORINGPARAMETERENTIRY> parameter(Pageable page);

	
	@Query(value = "select * from BAML_MON_ALERT_PARAM where del_flg='N' and ref_no =?1 ", nativeQuery = true)
	MONITORINGPARAMETERENTIRY findByIdCustom(String Id);
	
	
	@Query(value = "select max(ref_no) from BAML_MON_ALERT_PARAM ", nativeQuery = true)
	String getReferenceNo();
	
	@Query(value = "select count(*) from BAML_RULE_ENGINE_TABLE where rule_code = ?1", nativeQuery = true)
	String getrulecount(String RuleCode);
	
	
	
}