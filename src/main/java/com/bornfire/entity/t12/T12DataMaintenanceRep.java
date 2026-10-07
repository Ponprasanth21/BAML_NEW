package com.bornfire.entity.t12;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T12DataMaintenanceRep extends CrudRepository<T12DataMaintenance,String>{
	
	@Query(value = "select * from T12_IND_CASH_DEP_DETILS where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T12DataMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);

}
