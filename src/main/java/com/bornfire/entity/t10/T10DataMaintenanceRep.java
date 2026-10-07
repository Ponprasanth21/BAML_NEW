package com.bornfire.entity.t10;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface T10DataMaintenanceRep extends CrudRepository<T10DataMaintenance, String> {

	@Query(value = "select * from T10_DOM_INW_REMIT_DETAILS where report_date=?1 and tran_id=?2 and tran_date=?3 and part_tran_id=?4", nativeQuery = true)
	T10DataMaintenance getview(String d1, String tran_id, String tran_date, BigDecimal part_tran_id);

}
