package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RPT_View_Repo extends JpaRepository<Report_View, String> {

	Optional<Report_View> findById(String directorId);

	@Query(value = "select * from RPT_LIST_VIEW", nativeQuery = true)
	List<Report_View> getValidationList();

}
