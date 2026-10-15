package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
@Transactional
public interface ETLMonitorRep extends JpaRepository<EtlProcessInfo, String> {
	
	
	@Query(value = "select * from \"AML\".\"BAML_ETL_PROCESS_INFO_TB\" where \"MODULE_NAME\" in (select distinct \"MODULE_NAME\" from \"AML\".\"BAML_ETL_PROCESS_INFO_TB\") and \"START_TIME\" in (select max(\"START_TIME\") from \"AML\".\"BAML_ETL_PROCESS_INFO_TB\" group by \"MODULE_NAME\") order by \"START_TIME\" desc", nativeQuery = true)
	List<EtlProcessInfo> getEtlStatus();
	
	
	
}
