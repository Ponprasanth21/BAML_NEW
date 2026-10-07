package com.bornfire.entity.riskcust;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RISKREVIEWREP extends JpaRepository<RiskSuma, String> {

	@Query(value = "select * from table(BAML_CUST_RISK_REVIEW_FUN(?1,?2))", nativeQuery = true)
	RiskSuma RISKREVIEW(String fromdate, String todate);

	@Query(value = "select * from table(BAML_CUST_RISK_REVIEW_DETAIL_FUN(?1,?2))", nativeQuery = true)
	Page<RiskDetails> RISKREVIEWDetail(String fromdate, String todate, Pageable page);

}
