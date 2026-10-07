package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinUserProfileRep extends CrudRepository<FinUserProfileEntity,String> {
	@Query(value = "select * from BAML_USER_PROFILE_TABLE_FINACLE ORDER  BY EMP_NAME", nativeQuery = true)
	List<FinUserProfileEntity> getfin_user_details();
	
	
	@Query(value = "select user_id,sol_id,user_emp_id from BAML_USER_PROFILE_TABLE_FINACLE where user_id=?1 and sol_id =?2 and ", nativeQuery = true)
	List<Object[]> getfin_user_details(String userId);
	
	
	@Query(value = "select  gen.emp_id,gen.emp_name,gen.emp_email_id from fin_gen_emp_table gen where gen.emp_id=?1", nativeQuery = true)
	List<Object[]> getfin_gen_details(String user_emp_id);
	
	
	@Query(value = "select sol.sol_id,sol.sol_desc,sol.bank_code,sol.abbr_bank_name from fin_service_outlet_table sol  where sol.sol_id=?1", nativeQuery = true)
	List<Object[]> getfin_sol_details(String sol_id);
}
