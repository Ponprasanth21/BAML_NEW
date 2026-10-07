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
	
	@Query(value = "select dataid,versionnum from BAML_UNSC_ENTITY_TABLE where dataid=?1", nativeQuery = true)
	EntityTable getDataId(String directorId);
	
	@Query(value = "select reference_number from BAML_UNSC_ENTITY_TABLE where reference_number= ?1 ", nativeQuery = true)
	String findAlldataID(String reference_number);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE ", nativeQuery = true)
	Page<EntityTable> reportlist(Pageable page);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE where dataid=?1", nativeQuery = true)
	EntityTable FindByCustomId(String DATAID);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE", nativeQuery = true)
	Page<EntityTable> findAllRandom(Pageable pageable);

	@Query(value = "elect * from BAML_UNSC_ENTITY_TABLE ORDER BY DATAID desc", nativeQuery = true)
	Page<EntityTable> paramlist(Pageable page);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE ORDER BY DATAID desc", nativeQuery = true)
	Page<EntityTable> parameterlist(Pageable page);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE", nativeQuery = true)
	Page<EntityTable> SCRlist(Pageable page);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE ", nativeQuery = true)
	Page<EntityTable> rulelist(Pageable page);

	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE  ", nativeQuery = true)
	Page<EntityTable> paramlistforReport(Pageable page);
	
	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE where dataid=?1", nativeQuery = true)
	Page<EntityTable> getlistByDataId(Pageable page,String dataid);
	
	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE where reference_number=?1", nativeQuery = true)
	Page<EntityTable> getlistByRefNum(Pageable page,String dataid);
	
	@Query(value = "select * from BAML_UNSC_ENTITY_TABLE where  UPPER(FIRST_NAME)  like UPPER(?1) order by  dataid asc", nativeQuery = true)
	Page<EntityTable> getlistByFIRSTname(Pageable page,String lastname);
	
	@Query(value= "DELETE baml_unsc_entity_table where dataid !='%ENT'" ,nativeQuery = true)
	EntityTable deleteEnt();
	
}
