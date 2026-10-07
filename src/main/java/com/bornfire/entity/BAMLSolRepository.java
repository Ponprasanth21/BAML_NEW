package com.bornfire.entity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BAMLSolRepository extends JpaRepository<BAMLSolEntity, String>{
	
	
	 Optional<BAMLSolEntity> findById( String directorId); 
	 
	 @Query(value = "select * from BAML_SOL ", nativeQuery = true) 
	 Page<BAMLSolEntity> BankandBranchList(Pageable page);
	 
	 
	 @Query(value = "select * from BAML_SOL where sol_id=?1 ", nativeQuery = true) 
	 BAMLSolEntity findByIdcustom(String solId);
	
}
