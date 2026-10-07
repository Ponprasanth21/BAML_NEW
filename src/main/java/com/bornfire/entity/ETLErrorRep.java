package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
@Transactional
public interface ETLErrorRep extends JpaRepository<EtlErrorInfo, String> {
	
	
	@Query(value = "select * from BAML_ETL_ERROR_INFO_TB where module_name in (select distinct module_name from BAML_ETL_ERROR_INFO_TB) and recr_time in (select max(recr_time) from BAML_ETL_ERROR_INFO_TB group by module_name)", nativeQuery = true)
	List<EtlErrorInfo> getEtlError();
	
}
