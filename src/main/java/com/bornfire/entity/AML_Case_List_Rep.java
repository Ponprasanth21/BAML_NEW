package com.bornfire.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface AML_Case_List_Rep extends JpaRepository<AML_Cust_Case_Mgmt, String>{
	 
	
	Optional<AML_Cust_Case_Mgmt> findById(String directorId);


	  @Query(value = "select * from BAML_CUST_CASE_MGMT ", nativeQuery = true)
	 Page<AML_Cust_Case_Mgmt> caselist(Pageable page);
	 
	 @Query(value = "select * from BAML_CUST_CASE_MGMT where cust_id =?1 ", nativeQuery = true)
	 AML_Cust_Case_Mgmt findByIdCustom(String cust_id);


}
