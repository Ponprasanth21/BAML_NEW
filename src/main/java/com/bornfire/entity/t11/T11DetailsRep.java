package com.bornfire.entity.t11;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface T11DetailsRep  extends JpaRepository<T11Details,String> {
	 Optional<T11Details> findById( String directorId);
	 
	 
	 
	 
	 @Query(value = "select distinct cust_id,cust_name,riskrating from CUST_MAST_GEN_TABLE ", nativeQuery = true)
		List<Object[]>  findAllCustom();
	 
}