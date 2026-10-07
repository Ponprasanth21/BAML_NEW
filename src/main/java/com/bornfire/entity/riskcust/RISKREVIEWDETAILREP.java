package com.bornfire.entity.riskcust;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RISKREVIEWDETAILREP extends JpaRepository<RiskDetails, String> {

	@Query(value = "select * from table(BAML_CUST_RISK_REVIEW_DETAIL_FUN_TEST(?1,?2)) order by cif_id", nativeQuery = true)
	Page<RiskDetails> RISKREVIEWDetail(String fromdate, String todate, Pageable page);

	@Query(value = "select * from CUST_REVIEW_RISK_DETAIL_TEMP order by cif_id", nativeQuery = true)
	Page<RiskDetails> RISKREVIEWDetailpage(String fromdate, String todate, Pageable page);

	@Query(value = "select * from CUST_REVIEW_RISK_DETAIL_TEMP where cif_id =?1 ", nativeQuery = true)
	List<RiskDetails> RISKREVIEWDetailpageSearch(String Cust_Id);

}
