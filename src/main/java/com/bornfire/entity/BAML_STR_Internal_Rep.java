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
public interface BAML_STR_Internal_Rep extends JpaRepository<BAML_STR_Internal, String> {

	Optional<BAML_STR_Internal> findById(String directorId);

	@Query(value = "select * from BAML_STR_INTERNAL", nativeQuery = true)
    Page<BAML_STR_Internal> InternalList(Pageable pageable);
}
