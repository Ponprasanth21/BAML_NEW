package com.bornfire.entity.t3a;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T3ADataMaintenanceRep extends CrudRepository<T3ADataMaintenance,String>{
	
	@Query(value = "select * from T3_RBS_MASTER where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T3ADataMaintenance getview(String d1, String tran_id, String tran_date, String part_tran_id);

}


