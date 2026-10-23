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
public interface EntityTableRepository extends JpaRepository<EntityTable, String> {
	
	Optional<EntityTable> findById(String directorId);
	
	@Query(value = "select \"DATAID\",\"VERSIONNUM\" from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"DATAID\"=?1", nativeQuery = true)
	EntityTable getDataId(String directorId);
	
	@Query(value = "select \"REFERENCE_NUMBER\" from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"REFERENCE_NUMBER\"= ?1 ", nativeQuery = true)
	String findAlldataID(String reference_number);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" ", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> reportlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"DATAID\"=?1", nativeQuery = true)
	EntityTable FindByCustomId(String DATAID);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> findAllRandom(Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" ORDER BY \"DATAID\" desc", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> paramlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" ORDER BY \"DATAID\" desc", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> parameterlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> SCRlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" ", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> rulelist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"  ", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\"", nativeQuery = true)
	Page<EntityTable> paramlistforReport(Pageable page);
	
	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"DATAID\"=?1", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"DATAID\"=?1", nativeQuery = true)
	Page<EntityTable> getlistByDataId(Pageable page,String dataid);
	
	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"REFERENCE_NUMBER\"=?1", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"REFERENCE_NUMBER\"=?1", nativeQuery = true)
	Page<EntityTable> getlistByRefNum(Pageable page,String dataid);
	
	@Query(value = "select * from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where UPPER(\"FIRST_NAME\") like UPPER(?1) order by \"DATAID\" asc", countQuery = "select count(*) from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where UPPER(\"FIRST_NAME\") like UPPER(?1)", nativeQuery = true)
	Page<EntityTable> getlistByFIRSTname(Pageable page,String lastname);
	
	@Query(value= "DELETE from \"AML\".\"BAML_UNSC_ENTITY_TABLE\" where \"DATAID\" !='%ENT'" ,nativeQuery = true)
	EntityTable deleteEnt();
}
