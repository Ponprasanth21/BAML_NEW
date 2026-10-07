package com.bornfire.entity.t11;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface T11ReportsRep extends JpaRepository<T11Reports,Date> {
	 Optional<T11Reports> findById(Date directorId);
	 
	 @Query(value = "select * from T11_CDD_TRAN_TERM_TABLE", nativeQuery = true)
		Page<T11Reports> rejectList(Pageable page);
}
