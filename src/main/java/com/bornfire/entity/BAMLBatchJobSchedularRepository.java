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
public interface BAMLBatchJobSchedularRepository extends JpaRepository<BAMLBatchJobSchedular, String> {

	Optional<BAMLBatchJobSchedular> findById(String directorId);
	
	
	 @Query(value = "select * from BAML_BATCH_JOB_SCHEDULER ", nativeQuery = true) 
	 Page<BAMLBatchJobSchedular> getBAMLBatchJobSchedular(Pageable page);
	 
	 @Query(value = "select * from BAML_BATCH_JOB_SCHEDULER where job_id=?1 ", nativeQuery = true) 
	 BAMLBatchJobSchedular findByIdcustom(String jobId);
	
	

}
