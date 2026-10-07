package com.bornfire.entity.t11;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface T11DetailRep  extends JpaRepository<T11Details,String> {
	 Optional<T11Details> findById( String directorId);
	 
	 
	 @Query(value = "select * from T11_CDD_TRAN_TERM_DETAILS order by CUST_ID", nativeQuery = true)
		Page<T11Details> rejectList(Pageable page);
	 
	 @Query(value = "select distinct cust_id,cust_name,riskrating from CUST_MAST_GEN_TABLE ", nativeQuery = true)
		List<Object[]>  findAllCustom();
	 
}