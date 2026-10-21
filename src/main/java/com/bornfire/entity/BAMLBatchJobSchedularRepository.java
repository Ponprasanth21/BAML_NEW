package com.bornfire.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BAMLBatchJobSchedularRepository
        extends JpaRepository<BAMLBatchJobSchedular, String> {

    @Query(
        value = "SELECT * FROM \"AML\".\"BAML_BATCH_JOB_SCHEDULER\" ORDER BY \"JOB_ID\"",
        countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_BATCH_JOB_SCHEDULER\"",
        nativeQuery = true
    )
    Page<BAMLBatchJobSchedular> getBAMLBatchJobSchedular(Pageable page);

    @Query(
        value = "SELECT * FROM \"AML\".\"BAML_BATCH_JOB_SCHEDULER\" WHERE TRIM(\"JOB_ID\") = TRIM(?1)",
        nativeQuery = true
    )
    BAMLBatchJobSchedular findByIdcustom(String jobId);
}