package com.bornfire.entity.t20;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface T20ReportRep  extends JpaRepository<T20Report,String> {
	 Optional<T20Report> findById( String directorId);

	
	 
	 

}
