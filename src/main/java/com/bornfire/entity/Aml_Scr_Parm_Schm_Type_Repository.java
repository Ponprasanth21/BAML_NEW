package com.bornfire.entity;


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
public interface Aml_Scr_Parm_Schm_Type_Repository extends JpaRepository<Aml_Scr_Parm_Schm_Type_Entity, String> {

	Optional<Aml_Scr_Parm_Schm_Type_Entity> findById(String directorId);

	@Query(value = "select * from BAML_SCR_PARM_SCHM_TYPE  where delflag='N'", nativeQuery = true)

	Page<Aml_Scr_Parm_Schm_Type_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_SCR_PARM_SCHM_TYPE where DEL_FLAG='N' ", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> paramlist(Pageable page);

	@Query(value = "select * from BAML_SCR_PARM_SCHM_TYPE where DEL_FLAG='N' order by entity_flag,REF_NO asc", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlist(Pageable page);
	

	@Query(value = "select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS, "
			+ " DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE, "
		+ " DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE, "
		+ " CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE"
		+ "  where DEL_FLAG='N' and REF_NO =?1 order by entity_flag,REF_NO asc ", nativeQuery = true)
Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecoderefno(String values,Pageable page);
	
@Query(value = "select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS, "
		+ " DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE, "
	+ " DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE, "
	+ " CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE"
	+ "  where DEL_FLAG='N' and LOAN_LIMIT =?1 order by entity_flag,REF_NO asc ", nativeQuery = true)
Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeloan(String values,Pageable page);

@Query(value = "select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS, "
		+ " DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE, "
	+ " DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE, "
	+ " CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE"
	+ "  where DEL_FLAG='N' and upper(LOAN_SCHEME) like %?1% order by entity_flag,REF_NO asc ", nativeQuery = true)
Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeloanschm(String values,Pageable page);

@Query(value = "select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS, "
		+ " DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE, "
	+ " DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE, "
	+ " CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE"
	+ "  where DEL_FLAG='N' and SCHM_CODE  like %?1% order by entity_flag,REF_NO asc ", nativeQuery = true)
Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeschm(String values,Pageable page);



@Query(value = "select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS, "
		+ " DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE, "
	+ " DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE, "
	+ " CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE"
	+ "  where DEL_FLAG='N' and GL_SUB_HEAD_CODE like %?1% order by entity_flag,REF_NO asc ", nativeQuery = true)
Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeglsub(String values,Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_SCR_PARM_SCHM_TYPE set DEL_FLAG ='Y' where CIF_ID =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from BAML_SCR_PARM_SCHM_TYPE where DEL_FLAG='N' AND ENTITY_FLAG='Y' ", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> paramlistforReport(Pageable page);
	

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE BAML_SCR_PARM_SCHM_TYPE_NUM SET ID=(SELECT ID+1 FROM BAML_SCR_PARM_SCHM_TYPE_NUM)"
	 * , nativeQuery = true) void updatescr_param_schm_NumTB();
	 */
	
	  @Query(value = "select * from BAML_SCR_PARM_SCHM_TYPE  where DEL_FLAG='N' ORDER BY  REF_NO ASC" , nativeQuery = true)
	     List<Aml_Scr_Parm_Schm_Type_Entity> findAllCustom();
}

