package com.bornfire.entity.t8;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface T8ModRep extends JpaRepository<T8ReportMod,Date> {
	Optional<T8ReportMod> findById(Date directorId);
	
	@Query(value="select * from T8_TRAN_CUST_TYPE_MOD_TABLE ",nativeQuery = true)
	T8ReportMod  gett8details(Date reportDate);
}
