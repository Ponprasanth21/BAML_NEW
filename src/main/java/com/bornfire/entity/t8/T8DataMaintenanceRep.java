package com.bornfire.entity.t8;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T8DataMaintenanceRep extends CrudRepository<T8DataMaintenance,String>{
	
	@Query(value = "select * from T8_TRAN_CUST_TYPE_DETAIL where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T8DataMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);


}
