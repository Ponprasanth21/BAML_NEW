package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
@Transactional
public interface ETLMonitorRep extends JpaRepository<EtlProcessInfo, String> {
	
	
	@Query(value = "select * from BAML_etl_process_info_tb where module_name in (select distinct module_name from BAML_etl_process_info_tb) and start_time in (select max(start_time) from BAML_etl_process_info_tb group by module_name)", nativeQuery = true)
	List<EtlProcessInfo> getEtlStatus();
	
	
	
}
