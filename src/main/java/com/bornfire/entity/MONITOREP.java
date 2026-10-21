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

	@Query(value = "select * from \"AML\".\"BAML_MON_ALERT_PARAM\" where \"DEL_FLG\"='N' or \"DEL_FLG\" is null order by \"REF_NO\"",
		   countQuery = "select count(*) from \"AML\".\"BAML_MON_ALERT_PARAM\" where \"DEL_FLG\"='N' or \"DEL_FLG\" is null",
		   nativeQuery = true)
	Page<MONITORINGPARAMETERENTIRY> parameter(Pageable page);

	
	@Query(value = "select * from \"AML\".\"BAML_MON_ALERT_PARAM\" where (\"DEL_FLG\"='N' or \"DEL_FLG\" is null) and \"REF_NO\" = ?1 ", nativeQuery = true)
	MONITORINGPARAMETERENTIRY findByIdCustom(String Id);
	
	
	@Query(value = "select max(\"REF_NO\") from \"AML\".\"BAML_MON_ALERT_PARAM\" ", nativeQuery = true)
	String getReferenceNo();
	
	@Query(value = "select count(*) from \"AML\".\"BAML_RULE_ENGINE_TABLE\" where \"RULE_CODE\" = ?1", nativeQuery = true)
	String getrulecount(String RuleCode);
	
	
	
}