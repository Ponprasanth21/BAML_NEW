package com.bornfire.entity.t10;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t12.T12Detail;

@Repository
@Transactional
public interface T10DetailRepo extends JpaRepository<T10Detail, Date> {

	@Query(value = "select * from T10_DOM_INW_REMIT_DETAILS  where report_date = ?1 and cell_mapping =?2", nativeQuery = true)
	Page<T10Detail> detailList(Date todate, String filter, Pageable page);

	@Query(value = "select * from T10_DOM_INW_REMIT_DETAILS  where report_date = ?1", nativeQuery = true)
	Page<T10Detail> detailList1(Date todate, Pageable page);

	@Query(value = "select * from T10_DOM_INW_REMIT_DETAILS where report_date=?1", nativeQuery = true)
	Page<T10Detail> getReportList(String report_date, Pageable page);

	@Query(value = "select * from T10_DOM_INW_REMIT_DETAILS where report_date=?1 and cust_id=?2", nativeQuery = true)
	T10Detail getview(String d1, String cif_id);
}
