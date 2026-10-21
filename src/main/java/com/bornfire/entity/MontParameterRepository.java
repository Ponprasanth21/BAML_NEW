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
 

		  @Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL ORDER BY \"SRL_NO\"",
				countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL",
				nativeQuery = true)
		 Page<Monitoringparameter> paramlist(Pageable page);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL ORDER BY \"ENTITY_FLAG\", \"SRL_NO\"",
				countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL",
				nativeQuery = true) 
			 Page<Monitoringparameter> parameterlist(Pageable page);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_MON_ALERT_PARAM\"",
				countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_MON_ALERT_PARAM\"",
				nativeQuery = true) 
			 Page<MONITORINGPARAMETERENTIRY> parameter(Pageable page);
		  
			@Modifying
			@Query(value = "UPDATE \"AML\".\"BAML_RULE_ENGINE_TABLE\" SET \"DEL_FLAG\" ='Y' WHERE \"SRL_NO\" =?1", nativeQuery = true)
			int findByfgdg1(String srl_no);
		  
			@Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE (\"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL) AND \"SRL_NO\" =?1 ", nativeQuery = true)
			 Monitoringparameter findByIdCustom(String Id);
	 
			
			@Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"RULE_CODE\" IS NOT NULL AND (\"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL)", nativeQuery = true)
		     List<Monitoringparameter> findAllCustom();

			@Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'SCRIPT TYPE' AND \"REF_REC_TYPE\" = 'SCR' AND \"REF_CODE\" = ?1", nativeQuery = true)
			String ScriptDesc(String refcode);
			
			@Query(value = "SELECT \"REF_CODE\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'SCRIPT TYPE' AND \"REF_REC_TYPE\" = 'SCR'", nativeQuery = true)
			List<String> Scripttype();
			
			
			@Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'EXECUTION TYPE' AND \"REF_REC_TYPE\" = 'EXE'", nativeQuery = true)
			List<String> Executiontype();	
			
			@Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'FREQUENCY' AND \"REF_REC_TYPE\" = 'FREQ'", nativeQuery = true)
			List<String> Frequencytype();

			
			@Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'CUSTOMER TYPE' AND \"REF_REC_TYPE\" = 'CUS'", nativeQuery = true)
			List<String> Customertype();	
			
			@Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'ACCOUNT TYPE' AND \"REF_REC_TYPE\" = 'ACT'", nativeQuery = true)
			List<String> Accounttype();
	 @Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'RULE TYPE' AND \"REF_REC_TYPE\" = 'RULE'", nativeQuery = true)
		List<String> ruletype();
	
	 @Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'RULE SUB TYPE' AND \"REF_REC_TYPE\" = 'SUBR'", nativeQuery = true)
		List<String> rulesubtype();
	
	 @Query(value = "SELECT \"REF_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'MONITORING RULE' AND \"REF_REC_TYPE\" = 'MONR'", nativeQuery = true)
		List<String> ruleCode();
	 
	 
	 
	 
	 @Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"DEL_FLAG\"='N' OR \"DEL_FLAG\" IS NULL",
			nativeQuery = true)
		Page<Monitoringparameter> rulelist(Pageable page);

		
		

		@Query(value = "SELECT * FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"RULE_CODE\"=?1", nativeQuery = true)
		List<Monitoringparameter> getRuleCodeData(String id);
		
		@Query(value = "SELECT DISTINCT \"RULE_CODE_DESC\" FROM \"AML\".\"BAML_RULE_ENGINE_TABLE\" WHERE \"RULE_CODE\"=?1", nativeQuery = true)
		String getDescription(String id);
		
}