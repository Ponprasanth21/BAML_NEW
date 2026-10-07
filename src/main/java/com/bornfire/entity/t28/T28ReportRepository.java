package com.bornfire.entity.t28;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface T28ReportRepository extends JpaRepository<T28Reports, String> {
	
	Optional<T28Reports> findById(String directorId);
	
	@Query(value = "select * from T28_AML_CFT_INF_TABLE ", nativeQuery = true) 
	T28Reports findByIdcustom(String srlno);
	
	@Query(value = "select * from T28_AML_CFT_INF_TABLE where REPORT_DATE=?1", nativeQuery = true)
	List<T28Reports> gett28Report(Date d1);
}

