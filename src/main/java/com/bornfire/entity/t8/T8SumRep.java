package com.bornfire.entity.t8;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface T8SumRep extends JpaRepository <T8Report,Date> {
	Optional<T8Report> findById(Date directorId);

	
	@Query(value = "SELECT SUM((C11B_TOT_TRAN_NOT_LOW)+(C11D_TOT_TRAN_NOT_MED)+(C11F_TOT_TRAN_NOT_HIG)) FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal8(String report_date);

//5
	@Query(value = "SELECT SUM((C11C_TOT_TRAN_TAMT_LOW)+(C11E_TOT_TRAN_TAMT_MED)+(C11G_TOT_TRAN_TAMT_HIG))as TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal9(String report_date);
	
	@Query(value = "SELECT SUM((C1B_CASH_DEP_NOT_LOW)+(C1D_CASH_DEP_NOT_MED)+(C1F_CASH_DEP_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal12(String report_date);

	@Query(value = "SELECT SUM((C1C_CASH_DEP_TAMT_LOW)+(C1E_CASH_DEP_TAMT_MED)+(C1G_CASH_DEP_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal13(String report_date);
	
	@Query(value = "SELECT SUM((C2B_CASH_WDL_NOT_LOW)+(C2D_CASH_WDL_NOT_MED)+(C2F_CASH_WDL_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal16(String report_date);
	
	@Query(value = "SELECT SUM((C2C_CASH_WDL_TAMT_LOW)+(C2E_CASH_WDL_TAMT_MED)+(C2G_CASH_WDL_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal18(String report_date);
	
	@Query(value = "SELECT SUM((C3B_BOM_INW_REM_NOT_LOW)+(C3D_BOM_INW_REM_NOT_MED)+(C3F_BOM_INW_REM_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal19(String report_date);

	@Query(value = "SELECT SUM((C3C_COM_INW_REM_TAMT_LOW)+(C3E_COM_INW_REM_TAMT_MED)+(C3G_COM_INW_REM_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal20(String report_date);
	
	@Query(value = "SELECT SUM((C4B_BOM_OUT_REM_NOT_LOW)+(C4D_BOM_OUT_REM_NOT_MED)+(C4F_BOM_OUT_REM_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal23(String report_date);

	@Query(value = "SELECT SUM((C4C_COM_OUT_REM_TAMT_LOW)+(C4E_COM_OUT_REM_TAMT_MED)+(C4G_COM_OUT_REM_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal24(String report_date);
	
	@Query(value = "SELECT SUM((C5B_CHQ_INW_TRAN_NOT_LOW)+(C5D_CHQ_INW_TRAN_NOT_MED)+(C5F_CHQ_INW_TRAN_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal27(String report_date);

	@Query(value = "SELECT SUM((C5C_CHQ_INW_TRAN_TAMT_LOW)+(C5E_CHQ_INW_TRAN_TAMT_MED)+(C5G_CHQ_INW_TRAN_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal28(String report_date);
	
	@Query(value = "SELECT SUM((C6B_CHQ_OUT_TRAN_NOT_LOW)+(C6D_CHQ_OUT_TRAN_NOT_MED)+(C6F_CHQ_OUT_TRAN_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal31(String report_date);

	@Query(value = "SELECT SUM((C6C_CHQ_OUT_TRAN_TAMT_LOW)+(C6E_CHQ_OUT_TRAN_TAMT_MED)+(C6G_CHQ_OUT_TRAN_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal32(String report_date);
	
	@Query(value = "SELECT SUM((C11B_TOT_TRAN_NOT_LOW)+(C11D_TOT_TRAN_NOT_MED)+(C11F_TOT_TRAN_NOT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal37(String report_date);

	@Query(value = "SELECT SUM((C11C_TOT_TRAN_TAMT_LOW)+(C11E_TOT_TRAN_TAMT_MED)+(C11G_TOT_TRAN_TAMT_HIG))AS TOTAL FROM T8_TRAN_CUST_TYPE_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal38(String report_date);
}
