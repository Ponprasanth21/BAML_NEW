package com.bornfire.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BAMLSolRepository extends JpaRepository<BAMLSolEntity, String> {

    @Query(
        value = "SELECT * FROM \"AML\".\"BAML_SOL\"",
        countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_SOL\"",
        nativeQuery = true
    )
    Page<BAMLSolEntity> BankandBranchList(Pageable page);

    @Query(
        value = "SELECT * FROM \"AML\".\"BAML_SOL\" WHERE \"SOL_ID\" = ?1",
        nativeQuery = true
    )
    BAMLSolEntity findByIdcustom(String solId);
}