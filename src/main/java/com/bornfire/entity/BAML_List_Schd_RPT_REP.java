package com.bornfire.entity;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BAML_List_Schd_RPT_REP extends JpaRepository<BAML_List_Schd_RPT_Entity, Date> {
	Optional<BAML_List_Schd_RPT_Entity> findById(Date id);

	

}