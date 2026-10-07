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
public interface CrsListRepository extends JpaRepository<CRS_TIN, String> {

	Optional<CRS_TIN> findById(String directorId);
	
	
	@Query(value = "select * from CRS_TIN_TABLE", nativeQuery = true)
	Page<CRS_TIN> getpeplistBycif(Pageable page);
}
