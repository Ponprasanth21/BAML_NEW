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
 

		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where DEL_FLG='N' ORDER BY ENTITY_FLAG,REF_REC_TYPE", nativeQuery = true)
		 Page<RefcodeEntity> refcodelist(Pageable page);
		  
		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where  UPPER(ref_desc) like UPPER(?1) and DEL_FLG='N'", nativeQuery = true)
			 List<RefcodeEntity> codelistsearch(String recordtype);
		  
		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where  UPPER(ref_rec_type) like UPPER(?1) and DEL_FLG='N'", nativeQuery = true)
		  List<RefcodeEntity> rectypelistsearch(String recordtype);
		  
		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where  UPPER(ref_rec_desc) like UPPER(?1) and DEL_FLG='N'", nativeQuery = true)
		  List<RefcodeEntity> recdesclistsearch(String recordtype);
		  
		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where  UPPER(ref_code) like UPPER(?1) and DEL_FLG='N'", nativeQuery = true)
		  List<RefcodeEntity> refcodelistsearch(String recordtype);
		  
		  
		  @Query(value = "select * from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'MONITORING RULE' and REF_REC_TYPE = 'MONR'", nativeQuery = true)
			
		     List<RefcodeEntity> findAllCustomList();
		 
		  
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE BAML_REFERENCE_CODE_TABLE set DEL_FLG ='Y' where REF_CODE =?1 and REF_REC_TYPE =?2"
	 * , nativeQuery = true) String findByfgdg1(String srl_no,String rectype);
	 */
		  
	 
			@Query(value = "select  Distinct REC_TYPE,REC_DESC from BAML_REFERENCE_CODE_TABLE ", nativeQuery = true)
			
		     List<RefcodeEntity> findAllCustom();

		
			@Query(value = "select * from BAML_REFERENCE_CODE_TABLE where REF_CODE =?1 and REF_REC_TYPE =?2" , nativeQuery = true)
			Optional<RefcodeEntity> getValue(String refcode, String recordtype);

			
			
	 
	
}