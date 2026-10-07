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
public interface AMLIndividualRepository extends JpaRepository<IndividualTable, String> {
	Optional<IndividualTable> findById(String directorId);
	
	@Query(value = "select dataid,versionnum from BAML_UNSC_INDIVIDUAL_TABLE where dataid=?1", nativeQuery = true)
	IndividualTable getDataId(String directorId);
	
	@Query(value = "select reference_number from BAML_UNSC_INDIVIDUAL_TABLE where reference_number= ?1 ", nativeQuery = true)
	String findAlldataID(String reference_number);

	@Query(value = "select * from baml_unsc_individual_table ", nativeQuery = true)
	Page<IndividualTable> reportlist(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table where dataid=?1", nativeQuery = true)
	IndividualTable FindByCustomId(String DATAID);

	@Query(value = "select * from baml_unsc_individual_table", nativeQuery = true)
	Page<IndividualTable> findAllRandom(Pageable pageable);

	@Query(value = "select * from baml_unsc_individual_table", nativeQuery = true)
	Page<IndividualTable> paramlist(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table order by dataid desc", nativeQuery = true)
	Page<IndividualTable> parameterlist(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table", nativeQuery = true)
	Page<IndividualTable> SCRlist(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table ", nativeQuery = true)
	Page<IndividualTable> rulelist(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table  ", nativeQuery = true)
	Page<IndividualTable> paramlistforReport(Pageable page);

	@Query(value = "select * from baml_unsc_individual_table where NATIONALITY_VALUE =?1 OR FIRST_NAME=?2", nativeQuery = true)
	IndividualTable getUnscListByNID_FirstName(String nid, String firstname);
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE AML_UNSC_INDIVIDUAL_TABLE SET ID=(SELECT ID+1 FROM negative_list_num)"
	 * , nativeQuery = true) void updateNegListNumTB();
	 * 
	 */
	@Query(value = "select * from baml_unsc_individual_table where  UPPER(FIRST_NAME)  like UPPER(?1) order by  dataid asc", nativeQuery = true)
	Page<IndividualTable> getlistByFIRSTname(Pageable page,String lastname);
	
	
	
	@Query(value = "select * from baml_unsc_individual_table where reference_number=?1", nativeQuery = true)
	Page<IndividualTable> getlistByRefnum(Pageable page,String reference_number);
	
	
	@Query(value = "select * from baml_unsc_individual_table where dataid=?1", nativeQuery = true)
	Page<IndividualTable> getlistByDataId(Pageable page,String Data_Id);
	
	
	@Query(value= "DELETE baml_unsc_individual_table where dataid <> LIKE %IND" ,nativeQuery = true)
	IndividualTable deleteInd();
}