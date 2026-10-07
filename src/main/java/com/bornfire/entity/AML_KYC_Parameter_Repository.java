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
public interface AML_KYC_Parameter_Repository extends JpaRepository<AML_KYC_Parameter, String>{
	 
	
	Optional<AML_KYC_Parameter> findById(String directorId);


	  @Query(value = "select * from BAML_KYC_PARAMETER_TABLE ", nativeQuery = true)
	 Page<AML_KYC_Parameter> paramlist(Pageable page);
	 
	  @Query(value = "select * from BAML_KYC_PARAMETER_TABLE ", nativeQuery = true) 
		 AML_KYC_Parameter parameterlist();
	  
	  

	  @Query(value = "select ref_desc from baml_reference_code_table where ref_code ='KYC NORMS' and ref_rec_type = 'KYC' ", nativeQuery = true) 
		 List<String> parameterDropdown();
	  
	  @Query(value = "select * from BAML_KYC_PARAMETER_TABLE  ", nativeQuery = true) 
		 Page<AML_KYC_Parameter> parameter(Pageable page);
		 
		 
		 
		 @Query(value = "select * from BAML_KYC_PARAMETER_TABLE ", nativeQuery = true)
		 AML_KYC_Parameter findByIdCustom(String srlno);
		 
		 
	
	  

}