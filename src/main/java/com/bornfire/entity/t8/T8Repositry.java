package com.bornfire.entity.t8;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bornfire.entity.t6.T6Report;



@Repository
public interface T8Repositry extends JpaRepository<T8Detail,String> {

	
	@Query(value="select * from T8_TRAN_CUST_TYPE_DETAIL a where REPORT_DATE = ?1",nativeQuery = true)
	Page<T8Detail> gett8details(String custId,Pageable pageable);


}