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
public interface BlackListRepository extends JpaRepository<BlackListEntity, String> {
	
	 Optional<BlackListEntity> findById( String directorId);
	  @Query(value="select * from BLACK_LIST  where delflag='N'", nativeQuery = true)

    Page<BlackListEntity> findAllRandom(Pageable pageable);
}

