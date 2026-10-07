package com.bornfire.entity.t14;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T14DataMaintenanceRep extends CrudRepository<T14DetailMaintenance,String>{
	
	@Query(value = "select * from T14_CHQ_INW_DETAILS where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T14DetailMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);

}