package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface CMGrepository extends JpaRepository<FinCMG,String> {
	 Optional<FinCMG> findById( String directorId);

	 @Query(value="select * from CUST_MAST_GEN_TABLE where cust_id=?1",nativeQuery = true)
		Page<FinCMG> findAllCustIdCustom(String custId,Pageable pageable);

		@Query(value = "select count(*) from BAML_CUST_MAST_TABLE", nativeQuery = true)
		long findCustomcount();
		
		@Query(value = "select * from CUST_MAST_GEN_TABLE where cust_id=?1", nativeQuery = true)
		FinCMG getCustomer(String custid);
		
      /*********************************************SEARCH DETAILS*********************************************/		
		
		@Query(value = "select * from CUST_MAST_GEN_TABLE where cust_id=?1", nativeQuery = true)
		List<FinCMG> getcustomerId(String custid2);
		
		@Query(value = "select * from CUST_MAST_GEN_TABLE where cust_name LIKE %?1%", nativeQuery = true)
		List<FinCMG> getcustomerName(String custName2);
		
		@Query(value = "select * from CUST_MAST_GEN_TABLE where cust_perm_phone_num = ?1", nativeQuery = true)
		List<FinCMG> getcustomerNUMBER(String custName2);
		
	
}