package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface MontParameterRepository extends JpaRepository<Monitoringparameter, String>{
 

		  @Query(value = "select * from BAML_RULE_ENGINE_TABLE where DEL_FLAG='N' ", nativeQuery = true)
		 Page<Monitoringparameter> paramlist(Pageable page);
		  
		  @Query(value = "select * from BAML_RULE_ENGINE_TABLE where DEL_FLAG='N' ORDER BY ENTITY_FLAG,SRL_NO ", nativeQuery = true) 
			 Page<Monitoringparameter> parameterlist(Pageable page);
		  
		  @Query(value = "select * from BAML_MON_ALERT_PARAM  ", nativeQuery = true) 
			 Page<MONITORINGPARAMETERENTIRY> parameter(Pageable page);
		  
			@Modifying
			@Query(value = "UPDATE BAML_RULE_ENGINE_TABLE set DEL_FLAG ='Y' where SRL_NO =?1", nativeQuery = true)
			String findByfgdg1(String srl_no);
		  
			@Query(value = "select * from BAML_RULE_ENGINE_TABLE where DEL_FLAG='N' and SRL_NO =?1 ", nativeQuery = true)
			 Monitoringparameter findByIdCustom(String Id);
	 
			
			@Query(value = "select * from BAML_RULE_ENGINE_TABLE where RULE_CODE is not null and DEL_FLAG='N'", nativeQuery = true)
			
		     List<Monitoringparameter> findAllCustom();

			@Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'SCRIPT TYPE' and REF_REC_TYPE = 'SCR' AND REF_CODE = ?1", nativeQuery = true)
			String ScriptDesc(String refcode);
			
			@Query(value = "select REF_code from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'SCRIPT TYPE' and REF_REC_TYPE = 'SCR'", nativeQuery = true)
			List<String> Scripttype();
			
			
			@Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'EXECUTION TYPE' and REF_REC_TYPE = 'EXE'", nativeQuery = true)
			List<String> Executiontype();	
			
			@Query(value = "select REF_DESC from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'FREQUENCY' and REF_REC_TYPE = 'FREQ'", nativeQuery = true)
			List<String> Frequencytype();

			
			@Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'CUSTOMER TYPE' and REF_REC_TYPE = 'CUS'", nativeQuery = true)
			List<String> Customertype();	
			
			@Query(value = "select REF_DESC from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'ACCOUNT TYPE' and REF_REC_TYPE = 'ACT'", nativeQuery = true)
			List<String> Accounttype();
	 @Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'RULE TYPE' and REF_REC_TYPE = 'RULE'", nativeQuery = true)
		List<String> ruletype();
	
	 @Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'RULE SUB TYPE' and REF_REC_TYPE = 'SUBR'", nativeQuery = true)
		List<String> rulesubtype();
	
	 @Query(value = "select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'MONITORING RULE' and REF_REC_TYPE = 'MONR'", nativeQuery = true)
		List<String> ruleCode();
	 
	 
	 
	 
	 @Query(value = "select * from BAML_RULE_ENGINE_TABLE  where del_flg='N'", nativeQuery = true)
		Page<Monitoringparameter> rulelist(Pageable page);

		
		

		@Query(value = "select * from BAML_RULE_ENGINE_TABLE where RULE_CODE=?1", nativeQuery = true)
		List<Monitoringparameter> getRuleCodeData(String id);
		
		@Query(value = "select DISTINCT RULE_CODE_DESC from BAML_RULE_ENGINE_TABLE where RULE_CODE=?1", nativeQuery = true)
		String getDescription(String id);
		
}