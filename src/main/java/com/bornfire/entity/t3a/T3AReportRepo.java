package com.bornfire.entity.t3a;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface T3AReportRepo extends JpaRepository<T3AReport, Date>{
	
	@Query(value = "select * from T3A_PROFILE_FACE_TO_FACE  where report_date = ?1", nativeQuery = true)
	List<T3AReport> reportList(String todate);

	@Query(value = "select * from T3B_PROFILE_NON_FACE_TO_FACE  where report_date = ?1", nativeQuery = true)
	List<T3AReport> reportListB(String todate);
	
	
	
	@Query(value = "select b_13_tot,g_13_tot,l_13_tot from T3A_PROFILE_FACE_TO_FACE where report_date=?1", nativeQuery = true)
	long findVal2(String report_date);
	
	@Query(value = "select b_13_tot from T3A_PROFILE_FACE_TO_FACE where report_date=?1", nativeQuery = true)
	long findVal2low(String report_date);
	
	@Query(value = "select g_13_tot from T3A_PROFILE_FACE_TO_FACE where report_date=?1", nativeQuery = true)
	long findVal2medium(String report_date);
	
	@Query(value = "select l_13_tot from T3A_PROFILE_FACE_TO_FACE where report_date=?1", nativeQuery = true)
	long findVal2high(String report_date);

	@Query(value = "SELECT SUM(B_13_TOT+G_13_TOT+L_13_TOT)  from T3A_PROFILE_FACE_TO_FACE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal3(String report_date);
	
	
	@Query(value = "SELECT SUM((C_13_TOT)+(E_13_TOT)+(H_13_TOT)+(J_13_TOT)+(M_13_TOT)+(O_13_TOT))AS TOTAL FROM T3A_PROFILE_FACE_TO_FACE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal7(String report_date);
	
	@Query(value = "SELECT SUM((D_13_TOT)+(F_13_TOT)+(I_13_TOT)+(K_13_TOT)+(N_13_TOT)+(P_13_TOT)) AS TOTAL from T3A_PROFILE_FACE_TO_FACE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal10(String report_date);
	
	

	@Query(value = "SELECT SUM((B_13_TOT)+(G_13_TOT)+(L_13_TOT)) as TOTAL FROM T3B_PROFILE_NON_FACE_TO_FACE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal5(String report_date);
}
