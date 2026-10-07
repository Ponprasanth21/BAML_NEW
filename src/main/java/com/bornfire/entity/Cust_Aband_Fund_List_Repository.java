package com.bornfire.entity;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface Cust_Aband_Fund_List_Repository extends JpaRepository<Cust_Aband_Fund_List_Entity, String> {

	Optional<Cust_Aband_Fund_List_Entity> findById(String directorId);

	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST  where delflag='N'", nativeQuery = true)

	Page<Cust_Aband_Fund_List_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> paramlist(Pageable page);

	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' order by entity_flag, cif_id asc ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_CUST_ABAND_FUND_LIST set DEL_FLAG ='Y' where foracid =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' AND ENTITY_FLAG='Y' ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> paramlistforReport(Pageable page);
	
	
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE CUST_HNWI_LIST_num SET ID=(SELECT ID+1 FROM CUST_HNWI_LIST_num)",
	 * nativeQuery = true) void updatePepNumTB();
	 */

	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' and CIF_ID like ?1% order by entity_flag, cif_id asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistBycif(Pageable page,String cif);
	
	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' and UPPER(ACCT_NAME)  like UPPER(?1) order by entity_flag, cif_id asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistBylastname(Pageable page,String ACCT_NAME);
	
	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' and UPPER(PROD_TYPE)  like UPPER(?1) order by entity_flag, cif_id asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistByprodType(Pageable page,String ACCT_NAME);

	
	
	@Query(value = "select b.acid from  BAML_ACCT_MAST_TABLE b where b.foracid=? ", nativeQuery = true)
	String update_Acid(String negativelistdatasetid);
	
	@Query(value = "select b.ACCT_OPN_DATE from  BAML_ACCT_MAST_TABLE b where b.foracid=? ", nativeQuery = true)
	Date update_AccOpnDate(String negativelistdatasetid);
	
	
	@Query(value = "select b.GL_SUB_HEAD_CODE from   BAML_ACCT_MAST_TABLE b where b.foracid=?  ", nativeQuery = true)
	String update_Gl_sub_head_Code(String negativelistdatasetid);
	
	
	@Query(value = "select b.SCHM_CODE from  BAML_ACCT_MAST_TABLE b where b.foracid=?  ", nativeQuery = true)
	String update_Schm_code(String negativelistdatasetid);
	
	@Query(value = "select b.SCHM_TYPE from  BAML_ACCT_MAST_TABLE b where b.foracid=? ", nativeQuery = true)
	String update_Schm_Type(String negativelistdatasetid);
	
	@Query(value = "select * from BAML_CUST_ABAND_FUND_LIST where DEL_FLAG='N' ", nativeQuery = true)
	List<Cust_Aband_Fund_List_Entity> paramlist();

}

