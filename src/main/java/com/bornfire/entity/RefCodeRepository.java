package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RefCodeRepository extends JpaRepository<RefcodeEntity, RefCodeMasterEmbeddedID>{
 

		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"DEL_FLG\" = 'N' ORDER BY \"ENTITY_FLAG\", \"REF_REC_TYPE\"",
				countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"DEL_FLG\" = 'N'",
				nativeQuery = true)
		 Page<RefcodeEntity> refcodelist(Pageable page);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE UPPER(\"REF_DESC\") LIKE UPPER(?1) AND \"DEL_FLG\" = 'N' ORDER BY \"ENTITY_FLAG\", \"REF_REC_TYPE\"", nativeQuery = true)
			 List<RefcodeEntity> codelistsearch(String recordtype);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE UPPER(\"REF_REC_TYPE\") LIKE UPPER(?1) AND \"DEL_FLG\" = 'N' ORDER BY \"ENTITY_FLAG\", \"REF_REC_TYPE\"", nativeQuery = true)
		  List<RefcodeEntity> rectypelistsearch(String recordtype);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE UPPER(\"REF_REC_DESC\") LIKE UPPER(?1) AND \"DEL_FLG\" = 'N' ORDER BY \"ENTITY_FLAG\", \"REF_REC_TYPE\"", nativeQuery = true)
		  List<RefcodeEntity> recdesclistsearch(String recordtype);
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE UPPER(\"REF_CODE\") LIKE UPPER(?1) AND \"DEL_FLG\" = 'N' ORDER BY \"ENTITY_FLAG\", \"REF_REC_TYPE\"", nativeQuery = true)
		  List<RefcodeEntity> refcodelistsearch(String recordtype);
		  
		  
		  @Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_REC_DESC\" = 'MONITORING RULE' AND \"REF_REC_TYPE\" = 'MONR'", nativeQuery = true)
			
		     List<RefcodeEntity> findAllCustomList();
		 
		  
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE BAML_REFERENCE_CODE_TABLE set DEL_FLG ='Y' where REF_CODE =?1 and REF_REC_TYPE =?2"
	 * , nativeQuery = true) String findByfgdg1(String srl_no,String rectype);
	 */
		  
	 
			@Query(value = "SELECT DISTINCT \"REC_TYPE\", \"REC_DESC\" FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\"", nativeQuery = true)
			
		     List<RefcodeEntity> findAllCustom();

		
			@Query(value = "SELECT * FROM \"AML\".\"BAML_REFERENCE_CODE_TABLE\" WHERE \"REF_CODE\" = ?1 AND \"REF_REC_TYPE\" = ?2", nativeQuery = true)
			Optional<RefcodeEntity> getValue(String refcode, String recordtype);

			
			
	 
	
}