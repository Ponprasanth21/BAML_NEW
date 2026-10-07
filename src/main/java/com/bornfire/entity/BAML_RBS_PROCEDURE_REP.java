package com.bornfire.entity;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BAML_RBS_PROCEDURE_REP extends JpaRepository<BAML_RBS_REPORT_PROCEDURE, String> {
	
}