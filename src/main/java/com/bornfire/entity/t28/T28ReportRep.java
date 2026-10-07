package com.bornfire.entity.t28;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T28ReportRep extends JpaRepository<T28Report, String> {
	
	//Optional<T28Report> findById(String directorId);
	
	@Query(value = "select * from T28_AML_CFT_INF ", nativeQuery = true) 
	T28Report findByIdcustom(String srlno);
}