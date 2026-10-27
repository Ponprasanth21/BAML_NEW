package com.bornfire.entity.t3a;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T3ADataMaintenanceRep extends CrudRepository<T3ADataMaintenance,String>{
	
	@Query(value = "select * from \"AML\".\"T3_RBS_MASTER\" where \"REPORT_DATE\"=?1 and \"TRAN_ID\"=?2 and \"TRAN_DATE\"=?3 and \"PART_TRAN_ID\"=?4", nativeQuery = true)
	T3ADataMaintenance getview(String d1, String tran_id, String tran_date, String part_tran_id);

}


