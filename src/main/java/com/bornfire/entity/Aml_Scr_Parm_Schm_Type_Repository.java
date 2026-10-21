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

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N'", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' ", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> paramlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' order by \"ENTITY_FLAG\", \"REF_NO\" asc", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N'",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecode(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"REF_NO\") like concat('%', upper(?1), '%') order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"REF_NO\") like concat('%', upper(?1), '%')",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecoderefno(String values, Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and cast(\"LOAN_LIMIT\" as text) like concat('%', ?1, '%') order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and cast(\"LOAN_LIMIT\" as text) like concat('%', ?1, '%')",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeloan(String values, Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"LOAN_SCHEME\") like concat('%', upper(?1), '%') order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"LOAN_SCHEME\") like concat('%', upper(?1), '%')",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeloanschm(String values, Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"SCHM_CODE\") like concat('%', upper(?1), '%') order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"SCHM_CODE\") like concat('%', upper(?1), '%')",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeschm(String values, Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"GL_SUB_HEAD_CODE\") like concat('%', upper(?1), '%') order by \"ENTITY_FLAG\" asc, \"REF_NO\" asc",
	       countQuery = "select count(*) from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' and upper(\"GL_SUB_HEAD_CODE\") like concat('%', upper(?1), '%')",
	       nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecodeglsub(String values, Pageable page);

	@Modifying
	@Query(value = "UPDATE \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" set \"DEL_FLAG\" ='Y' where \"REF_NO\" =?1", nativeQuery = true)
	int findByfgdg1(String negativelistdatasetid);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' AND \"ENTITY_FLAG\"='Y' order by \"REF_NO\" asc", nativeQuery = true)
	Page<Aml_Scr_Parm_Schm_Type_Entity> paramlistforReport(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_SCR_PARM_SCHM_TYPE\" where \"DEL_FLAG\"='N' ORDER BY \"REF_NO\" ASC", nativeQuery = true)
	List<Aml_Scr_Parm_Schm_Type_Entity> findAllCustom();

}
