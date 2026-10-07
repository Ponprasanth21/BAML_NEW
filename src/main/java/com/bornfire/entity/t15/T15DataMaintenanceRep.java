package com.bornfire.entity.t15;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface T15DataMaintenanceRep extends CrudRepository<T15DataMaintenance,String>{
	
	@Query(value = "select * from T15_CHQ_OUT_DETAILS where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T15DataMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);

}
