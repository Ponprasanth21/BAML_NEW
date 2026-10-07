package com.bornfire.entity.t27;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T27CDetRepo  extends JpaRepository<T27CDetail, String> {
	
	
	
	@Query(value = "select * from T27C_TRAN_NRE_DET_TABLE where report_date=?1", nativeQuery = true)
	Page<T27CDetail> getReportList(String report_date,Pageable page);

}
