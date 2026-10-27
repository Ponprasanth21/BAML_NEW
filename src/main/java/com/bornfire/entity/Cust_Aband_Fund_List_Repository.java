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

	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\"  where \"DEL_FLAG\"='N'", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> paramlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' order by \"ENTITY_FLAG\", \"CIF_ID\" asc ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" set \"DEL_FLAG\" ='Y' where \"FORACID\" =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' AND \"ENTITY_FLAG\"='Y' ", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> paramlistforReport(Pageable page);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' and \"CIF_ID\" like ?1% order by \"ENTITY_FLAG\", \"CIF_ID\" asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistBycif(Pageable page,String cif);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"ACCT_NAME\")  like UPPER(?1) order by \"ENTITY_FLAG\", \"CIF_ID\" asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistBylastname(Pageable page,String ACCT_NAME);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"PROD_TYPE\")  like UPPER(?1) order by \"ENTITY_FLAG\", \"CIF_ID\" asc", nativeQuery = true)
	Page<Cust_Aband_Fund_List_Entity> getabandlistByprodType(Pageable page,String ACCT_NAME);

	
	
	@Query(value = "select b.\"ACID\" from  \"AML\".\"BAML_ACCT_MAST_TABLE\" b where b.\"FORACID\"=? ", nativeQuery = true)
	String update_Acid(String negativelistdatasetid);
	
	@Query(value = "select b.\"ACCT_OPN_DATE\" from  \"AML\".\"BAML_ACCT_MAST_TABLE\" b where b.\"FORACID\"=? ", nativeQuery = true)
	Date update_AccOpnDate(String negativelistdatasetid);
	
	
	@Query(value = "select b.\"GL_SUB_HEAD_CODE\" from   \"AML\".\"BAML_ACCT_MAST_TABLE\" b where b.\"FORACID\"=?  ", nativeQuery = true)
	String update_Gl_sub_head_Code(String negativelistdatasetid);
	
	
	@Query(value = "select b.\"SCHM_CODE\" from  \"AML\".\"BAML_ACCT_MAST_TABLE\" b where b.\"FORACID\"=?  ", nativeQuery = true)
	String update_Schm_code(String negativelistdatasetid);
	
	@Query(value = "select b.\"SCHM_TYPE\" from  \"AML\".\"BAML_ACCT_MAST_TABLE\" b where b.\"FORACID\"=? ", nativeQuery = true)
	String update_Schm_Type(String negativelistdatasetid);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_ABAND_FUND_LIST\" where \"DEL_FLAG\"='N' ", nativeQuery = true)
	List<Cust_Aband_Fund_List_Entity> paramlist();

}

