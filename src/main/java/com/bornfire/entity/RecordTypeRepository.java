package com.bornfire.entity;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface RecordTypeRepository extends JpaRepository<RecordTypeEntity, String>{
	/*
	 * 
	 * @Query(value = "select * from REFERENCE_CODE_TABLE where DEL_FLG='N' ",
	 * nativeQuery = true) Page<RefcodeEntity> refcodelist(Pageable page);
	 * 
	 * 
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE REFERENCE_CODE_TABLE set DEL_FLG ='Y' where REF_CODE =?1",
	 * nativeQuery = true) String findByfgdg1(String srl_no);
	 */
	 
			@Query(value = "select distinct REF_REC_TYPE,REF_REC_DESC from BAML_REFERENCE_CODE_TABLE where del_flg = 'N'", nativeQuery = true)
			List<Object[]>  findAllCustom();

			
			 
			
	 
	
}