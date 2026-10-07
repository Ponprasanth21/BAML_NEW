package com.bornfire.entity.t9;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface T9DataMaintenanceRep extends CrudRepository<T9DataMaintenance,String>{
	
	@Query(value = "select * from T9_DOM_OW_REMIT_DETAILS where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T9DataMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);


}

