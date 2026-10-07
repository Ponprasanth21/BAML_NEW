package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RBSReportRepo extends JpaRepository<RBSReport, Integer> {
	

	
	@Query(value = "select * from BAML_RPT_MAST ORDER BY srl_no", nativeQuery = true)
	List<RBSReport> getReportList();
	
	
	
	@Query(value = "select * from BAML_RPT_MAST  where det_flg='Y' ORDER BY srl_no", nativeQuery = true)
	List<RBSReport> getReportList1();
	
}
