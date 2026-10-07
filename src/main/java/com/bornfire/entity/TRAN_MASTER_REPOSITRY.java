
package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;
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
public interface TRAN_MASTER_REPOSITRY extends JpaRepository<TRAN_MASTER, String> {
	Optional<TRAN_MASTER> findById(String directorId);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustom(Date formdate, Date todate, Pageable pageable);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where Tran_date between ?1 and ?2 ORDER BY tran_date DESC", nativeQuery = true)
	String findAlldatecount(Date today, Date fromdate);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where Tran_date=?1 ORDER BY tran_date DESC", nativeQuery = true)
	String findAlldatecount1(Date today);

	/*******************************************************
	 * TRANSACTION INQUIRY SEARCH
	 **************************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_crncy_code =?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANCRNCY(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where tran_crncy_code =?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANCRNCYcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where ref_num LIKE %?1% ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANREF(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where ref_num LIKE %?1% ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANREFcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANDATE(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANDATEcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where trim(tran_id) = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANID(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where trim(tran_id) = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANIDcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where trim(part_tran_srl_num) = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getPARTTRANID(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where trim(part_tran_srl_num) = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getPARTTRANIDcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where part_tran_type = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getPARTTRANTYPE(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where part_tran_type = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getPARTTRANTYPEcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_amt = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANAMT(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where tran_amt = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANAMTcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid in(select acid from bam where  substr(tam_deposit_status,1,2) LIKE '%?1%') ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANSTATUS(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where tr_status = ?1 ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANSTATUScount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid in (select acid from bam where foracid = ?1) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANACCT(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where acid in (select acid from bam where foracid = ?1) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANACCTcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid in(select acid from bam where acct_name like %?1%) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANCUST(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where acid in(select acid from bam where acct_name like %?1%) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANCUSTcount(String CustName2);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid in(select acid from bam where cif_id like %?1%) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getTRANCUSTID(String CustName2);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where acid in(select acid from bam where cif_id like %?1%) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	String getTRANCUSTIDcount(String CustName2);

	/********************************************************
	 * TRANSACTION INQUIRY SEARCH
	 ***************************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where Tran_date between ?1 and ?2 ORDER BY tran_date DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAlldate(Date fromdate, Date todate, Pageable pageable);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where Tran_date =?1 ORDER BY tran_date DESC", nativeQuery = true)
	String findAlldatecount(String fromdate);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where Tran_date between ?1 and ?2 ORDER BY tran_date DESC", nativeQuery = true)
	String findAlldatecount(String fromdate, String todate);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_id=?1", nativeQuery = true)
	Page<TRAN_MASTER> findAlltranIdCustom(String tranId, Pageable pageable);

	/*******************************************************************************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'C' AND CUST_ID IS NOT NULL and tran_sub_type = 'NR' and part_tran_type ='C'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomList(BigDecimal value_min, BigDecimal value_max, Date today, Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'C' and tran_sub_type = 'NR' ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC"
	 * , nativeQuery = true) String getCountOF( BigDecimal value_min, BigDecimal
	 * value_max,String today);
	 */
	/*********************************************************
	 * dc
	 **********************************************************/

	/***************************************************************
	 * HVCWL
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'C' AND CUST_ID IS NOT NULL and tran_sub_type = 'NP' and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListHVCWL(BigDecimal value_min, BigDecimal value_max, Date today, Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'C' and tran_sub_type = 'NP' and part_tran_type ='D' ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC"
	 * , nativeQuery = true) String getCountOFHVCWL( BigDecimal value_min,
	 * BigDecimal value_max,String today);
	 */
	/*****************************************************************
	 * HVCWL
	 **************************************************/

	/***************************************************************
	 * HVNCD
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'T' AND CUST_ID IS NOT NULL and tran_sub_type in ('IC','BI','CI','I') and part_tran_type ='C'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListHVNCD(BigDecimal value_min, BigDecimal value_max, Date today, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type != 'C' AND CUST_ID IS NOT NULL and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVNCW1(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type = 'C' and tran_sub_type = 'NR' AND CUST_ID IS NOT NULL  and part_tran_type ='C'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVCDP(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and  tran_type = 'T' and part_tran_type ='C' AND CUST_ID IS NOT NULL  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVNCD(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and CUST_ID IS NOT NULL and tran_type = 'T' and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVNCW(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type = 'C'and tran_sub_type = 'NP' AND CUST_ID IS NOT NULL and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVCWL(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type != 'C' AND CUST_ID IS NOT NULL and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVCDP1(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type = 'C'and tran_sub_type = 'NP' AND CUST_ID IS NOT NULL and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVCWL1(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and  tran_type = 'T' and part_tran_type ='C' AND CUST_ID IS NOT NULL  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getCVNCD1(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type = 'C' AND CUST_ID IS NOT NULL and part_tran_type ='C'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getTSCTP(String acid, Date fromdate, Date todate, Pageable page);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where acid =?1 and tran_date between ?2 and ?3  and tran_type = 'C' AND CUST_ID IS NOT NULL and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> getDTCDT(String acid, Date fromdate, Date todate, Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'T' and tran_sub_type in ('IC','BI','CI','I') and part_tran_type ='C' ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC"
	 * , nativeQuery = true) String getCountOFHVNCD( BigDecimal value_min,
	 * BigDecimal value_max,String today);
	 */
	/*****************************************************************
	 * HVNCD
	 **************************************************/

	/***************************************************************
	 * HVNCW
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'T' AND CUST_ID IS NOT NULL  and tran_sub_type in ('BC','BI','CI','IP') and part_tran_type ='D'  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListHVNCW(BigDecimal value_min, BigDecimal value_max, Date today, Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where TRAN_AMT between ?1 and ?2 and tran_date =?3 and tran_type = 'T' and tran_sub_type in ('BC','BI','CI','IP') and part_tran_type ='D' ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC"
	 * , nativeQuery = true) String getCountOFHVNCW( BigDecimal value_min,
	 * BigDecimal value_max,String today);
	 */
	/*****************************************************************
	 * HVNCW
	 **************************************************/

	/***************************************************************
	 * CVCDP
	 ****************************************************/
	@Query(value = "  select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NR' AND CUST_ID IS NOT NULL  and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NR' and part_tran_type ='C' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVCDP(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NR' and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NR' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVCDP(String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCDP
	 **************************************************/

	/***************************************************************
	 * CVCWL
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NP' AND CUST_ID IS NOT NULL and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NP' and part_tran_type ='D' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVCWL(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NP' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NP' and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVCWL( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCWL
	 **************************************************/

	/***************************************************************
	 * CVCND
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' and part_tran_type ='C' AND CUST_ID IS NOT NULL and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='C' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVNCD(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVCND( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCND
	 **************************************************/

	/***************************************************************
	 * CVCNW
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 AND CUST_ID IS NOT NULL and tran_type = 'T' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'T' AND CUST_ID IS NOT NULL and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVNCW(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVNCW( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCNW
	 **************************************************/

	/***************************************************************
	 * CVCDP1
	 ****************************************************/
	@Query(value = "  select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C' AND CUST_ID IS NOT NULL and tran_sub_type = 'NR' and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_sub_type = 'NR' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVCDP1(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NR' and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NR' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVCDP1(String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCDP1
	 **************************************************/

	/***************************************************************
	 * CVCWL1
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NP' AND CUST_ID IS NOT NULL and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NP' AND CUST_ID IS NOT NULL and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVCWL1(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'C'and tran_sub_type = 'NP' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'C'and tran_sub_type = 'NP' and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVCWL1( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCWL1
	 **************************************************/

	/***************************************************************
	 * CVCND1
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' AND CUST_ID IS NOT NULL and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'T' AND CUST_ID IS NOT NULL and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVNCD1(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' and part_tran_type ='C' and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVNCD1( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCND1
	 **************************************************/

	/***************************************************************
	 * CVCNW1
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 AND CUST_ID IS NOT NULL and tran_type = 'T' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='D' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCVNCW1(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*
	 * @Query(value =
	 * "select count(*) from BAML_ACCT_TRANS_TABLE where tran_date between ?1 and ?2 and tran_type = 'T' and part_tran_type ='D' and acid in (select acid from BTM where tran_type = 'T' and part_tran_type ='D' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by acid)  ORDER BY acid DESC"
	 * , nativeQuery = true) String getCountOFCVNCW1( String fromDate ,String
	 * today,BigDecimal value_min, BigDecimal value_max);
	 */
	/*****************************************************************
	 * CVCNW1
	 **************************************************/

	/***************************************************************
	 * DTCDT
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 and tran_type = 'C'  and part_tran_type ='C'  and cust_id in (select cust_id from BTM where tran_type = 'C' and part_tran_type ='C' and tran_date = ?1  having sum(tran_amt) >= ?2 group by cust_id)", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListDTCDT(Date today, BigDecimal value_min, Pageable page);

	/*****************************************************************
	 * DTCDT
	 **************************************************/

	/*****************************************************************
	 * TSCTP
	 **************************************************/

	@Query(value = "select * from BTM where tran_type = 'C' and part_tran_type ='C' and tran_date between ?1 and ?2 and CUST_ID in (select CUST_ID from BTM where tran_type = 'C' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by CUST_ID) ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListTSCTP(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * TSCTP
	 **************************************************/

	/*****************************************************************
	 * TSCTP1
	 **************************************************/

	@Query(value = "select * from BTM where tran_type = 'C' and part_tran_type ='C' and tran_date between ?1 and ?2 and CUST_ID in (select CUST_ID from BTM where tran_type = 'C' and part_tran_type ='C' and tran_date between ?1 and ?2  having sum(tran_amt) >= ?3 group by CUST_ID) ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListTSCTP1(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * TSCTP1
	 **************************************************/

	/***************************************************************
	 * CTACP
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 and tran_type = 'C' and cust_id in (select cust_id from BTM where tran_type = 'C' and tran_date = ?1 and tran_amt > ?2 group by cust_id) order by tran_particular_code DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCTACP(Date today, BigDecimal value_min, Pageable page);

	/*****************************************************************
	 * CTACP
	 **************************************************/

	/***************************************************************
	 * STVID
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 and tran_type = 'C' and cust_id in (select cust_id from BTM where tran_type = 'C' and tran_date = ?1 and tran_amt > ?2 group by cust_id) order by tran_particular_code DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListSTVID(Date today, BigDecimal value_min, Pageable page);

	/*****************************************************************
	 * STVID
	 **************************************************/

	/*****************************************************************
	 * CTVID
	 **************************************************/

	@Query(value = "select * from BTM where tran_type = 'C' and tran_date between ?1 and ?2 and CUST_ID in (select CUST_ID from BTM where tran_type = 'C'  and tran_date between ?1 and ?2  and tran_amt > ?3 group by CUST_ID) ORDER BY acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCTVID(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * CTVID
	 **************************************************/

	/***************************************************************
	 * STVET
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 AND CUST_ID IS NOT NULL and tran_type = 'C' and acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date = ?1 and tran_amt > ?2 group by acid) order by acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListSTVET(Date today, BigDecimal value_min, Pageable page);

	/*****************************************************************
	 * STVET
	 **************************************************/

	/*****************************************************************
	 * NTVET
	 **************************************************/

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_type = 'C' and tran_date between ?1 and ?2  AND CUST_ID IS NOT NULL and tr_status in ('D','I') and acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  and tr_status in ('D','I') having count(tran_id) > ?3 group by acid) order by acid DESC,tran_date DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListNTVET(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * NTVET
	 **************************************************/

	/***************************************************************
	 * TATLS
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 AND CUST_ID IS NOT NULL and tran_type = 'C' and acid in (select acid from BTM where tran_type = 'C' and tran_date = ?1 AND CUST_ID IS NOT NULL and tran_amt > ?2 group by acid) order by acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListTATLS(Date today, BigDecimal value_min, Pageable page);

	/*****************************************************************
	 * TATLS
	 **************************************************/

	/*****************************************************************
	 * NATLS
	 **************************************************/

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  and tr_status in ('D','I') and acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2  and tr_status in ('D','I') having count(tran_id) > ?3 group by acid) order by acid DESC,tran_date DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListNATLS(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * NATLS
	 **************************************************/

	/*****************************************************************
	 * RTBLP
	 **************************************************/

	@Query(value = "select * from btm where acid in (select acid from btm where tran_amt < ?3 and tran_date between ?1 and ?2 having count(*) > 1 group by acid,tran_type,part_tran_type,tran_amt ) and tran_amt in (select tran_amt from btm where tran_amt < ?3 and tran_date between ?1 and ?2 having count(*) > 1 group by acid,tran_type,part_tran_type,tran_amt) AND CUST_ID IS NOT NULL and tran_date between ?1 and ?2 order by acid,tran_amt,part_tran_type", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListRTBLP(Date fromDate, Date today, BigDecimal value_min, BigDecimal value_max,
			Pageable page);

	/*****************************************************************
	 * RTBLP
	 **************************************************/

	/***************************************************************
	 * CTBTL
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 AND CUST_ID IS NOT NULL and tran_type = 'C' and tran_amt < ?2 and  acid in (select acid from BTM where tran_type = 'C' and tran_date = ?1 AND CUST_ID IS NOT NULL and tran_amt < ?2 group by acid) order by acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCTBTL(Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * CTBTL
	 **************************************************/
	/***************************************************************
	 * NCTBT
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 AND CUST_ID IS NOT NULL and tran_type = 'C' and tran_amt < ?2 and acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date = ?1 and tran_amt < ?2 group by acid) order by acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListNCTBT(Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * NCTBT
	 **************************************************/
	/***************************************************************
	 * RTBTL
	 ****************************************************/
	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1 AND CUST_ID IS NOT NULL and tran_type = 'C' and tran_amt < ?2 and  acid in (select acid from BTM where tran_type = 'C' AND CUST_ID IS NOT NULL and tran_date = ?1 and tran_amt < ?2 group by acid) order by acid DESC", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListRTBTL(Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * CUTMA
	 **************************************************/
	/***************************************************************
	 * RTBTL
	 ****************************************************/
	@Query(value = "select * from btm where tran_date=?1 and cust_id in (select cust_id from BAML_CUST_MAST_TABLE where num_of_accounts > 1 )order by acid,part_tran_type,cust_id", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListCUTMA(Date today, BigDecimal lowvalue, Pageable page);

	/*****************************************************************
	 * CUTMA
	 **************************************************/
	/*****************************************************************
	 * ATBTL
	 **************************************************/

	@Query(value = "select * from btm where tran_date between ?1 and ?2 AND CUST_ID IS NOT NULL and acid in (select acid from btm where tran_date between ?1 and ?2 AND CUST_ID IS NOT NULL  having sum(tran_amt) >= ?3 group by acid) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListATBTL(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * ATBTL
	 **************************************************/

	/***************************************************************
	 * MTOOR
	 ****************************************************/
	@Query(value = "select * from btm where tran_date = ?1 and part_tran_type = 'C' and tran_type ='T'AND CUST_ID IS NOT NULL  order by acid,tran_amt", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListMTOOR(Date today, BigDecimal lowvalue, Pageable page);

	/*****************************************************************
	 * MTOOR
	 **************************************************/

	/***************************************************************
	 * OTOMR
	 ****************************************************/
	@Query(value = "select * from btm where tran_date = ?1 and part_tran_type = 'D' and tran_type ='T' AND CUST_ID IS NOT NULL order by acid,tran_amt", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListOTOMR(Date today, BigDecimal lowvalue, Pageable page);

	/*****************************************************************
	 * OTOMR
	 **************************************************/

	/***************************************************************
	 * TINCP
	 ****************************************************/
	@Query(value = "select * from btm a where a.tran_date = ?1 AND a.CUST_ID IS NOT NULL and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date = ?1  and a.part_tran_type = 'C' and a.tran_amt > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListTINCP(Date today, BigDecimal lowvalue, Pageable page);

	/*****************************************************************
	 * TINCP
	 **************************************************/
	/*****************************************************************
	 * VINCP
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 AND a.CUST_ID IS NOT NULL and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListVINCP(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * VINCP
	 **************************************************/
	/*****************************************************************
	 * VINCP
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListFIFOE(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * VINCP
	 **************************************************/
	/*****************************************************************
	 * MRTSB
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListMRTSB(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * MRTSB
	 **************************************************/
	/*****************************************************************
	 * RRTSB
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListRRTSB(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * RRTSB
	 **************************************************/
	/*****************************************************************
	 * FINWC
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListFINWC(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * FINWC
	 **************************************************/
	/*****************************************************************
	 * FOUTC
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListFOUTC(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * FOUTC
	 **************************************************/
	/*****************************************************************
	 * SODWE
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListSODWE(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * SODWE
	 **************************************************/
	/*****************************************************************
	 * RSINO
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListRSINO(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * RSINO
	 **************************************************/
	/*****************************************************************
	 * LVDFS
	 **************************************************/

	@Query(value = "select * from btm a where a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' and a.acid in (select a.acid from btm a, bam b, bcm c where a.acid=b.acid and b.cust_id=c.cust_id and a.tran_date between ?1 and ?2 and a.part_tran_type = 'C' having sum(a.tran_amt) > (c.annual_salary_income/12) group by a.acid,(c.annual_salary_income)/12 ) order by acid", nativeQuery = true)
	Page<TRAN_MASTER> findAllCustomListLVDFS(Date fromDate, Date today, BigDecimal threshold, Pageable page);

	/*****************************************************************
	 * LVDFS
	 **************************************************/

	@Query(value = "select value_date from BAML_ACCT_TRANS_TABLE WHERE TRAN_AMT between ?1 and ?2", nativeQuery = true)
	Date getDate(String id);

	/*
	 * @Query(value =
	 * "select to_char(a.TRAN_DATE,'dd-mm-yyyy')tran_date,b.FORACID,b.ACCT_NAME,a.PART_TRAN_TYPE,a.TRAN_ID,a.PART_TRAN_SRL_NUM,b.CUST_ID,a.TRAN_CRNCY_CODE,a.TRAN_AMT,a.TRAN_PARTICULAR,a.MODULE_ID,a.acid from BAML_ACCT_TRANS_TABLE a,BAML_ACCT_MAST_TABLE b where b.ACID = a.ACID AND a.acid in(select acid from BAML_ACCT_MAST_TABLE where CUST_ID =?1 ) ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC"
	 * , nativeQuery = true) List<Object[]> getAccountDetailsCust(String CUSTID);
	 */

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id = ?1)  ORDER BY tran_date DESC,tran_id DESC,part_tran_srl_num ASC", nativeQuery = true)
	List<TRAN_MASTER> getAccountDetailsCust(String CUSTID);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date between ?1 and ?2 and cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id = ?3) ", nativeQuery = true)
	List<TRAN_MASTER> getAccountDetailsCustdate(Date fromdate, Date todate, String acid);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where  tran_date between ?1 and ?2 and cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id = ?3)  ", nativeQuery = true)
	String TranCount(Date fromdate, Date todate, String acid);

	@Query(value = "select count(*) from BAML_ACCT_TRANS_TABLE where cust_id in (select cust_id from BAML_CUST_MAST_TABLE where cif_id = ?1) ", nativeQuery = true)
	String TranCount(String acid);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_date =?1", nativeQuery = true)
	List<TRAN_MASTER> getSTRTranDet(Date tran_date);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  tran_id =?1", nativeQuery = true)
	List<TRAN_MASTER> getSTRTranIdDet(String tran_id);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where  part_tran_srl_no =?1", nativeQuery = true)
	List<TRAN_MASTER> getSTRTranSrlDet(String part_tran_srl_no);

	@Query(value = "select b.cust_id,to_char(b.tran_date,'dd/MM/yyyy')tran_date,b.tran_id,b.part_tran_srl_num,b.tran_type,b.tran_sub_type,b.part_tran_type,b.tran_crncy_code,b.tran_amt,b.tran_particular,b.fx_tran_amt,to_char(b.pstd_date,'dd/MM/yyyy')pstd_date,(Case when b.rate_code = 'b.rate_code' then '' else '' end) as rate_code,a.foracid,a.acct_name,to_char(a.acct_opn_date,'dd/MM/yyyy')acct_opn_date,to_char(a.acct_cls_date,'dd/MM/yyyy')acct_cls_date,(Case when c.cntry_code = 'MU' then 'Mauritius' else '' end) as cntry_code,c.CUST_SECTOR_CODE,c.ADDRESS1,c.ADDRESS2,c.CITY_CODE,c.PREFERREDPHONE,(Case when c.cust_sex = 'M' then 'Male' when c.cust_sex = 'F' then 'Female' else 'Other' end) as SEX,c.CUST_FIRST_NAME,c.CUST_LAST_NAME,(Case when c.nationality = 'MU' then 'Mauritius' else '' end) as nationality,to_char(c.cust_dob,'dd/MM/yyyy')cust_dob\r\n"
			+ "from baml_acct_mast_table a,BAML_ACCT_TRANS_TABLE b,BAML_CUST_MAST_TABLE c where a.acid=b.acid and b.cust_id=c.cust_id and trim(b.tran_id) =?1 and b.tran_date =?2 and trim(b.part_tran_srl_num) =?3", nativeQuery = true)
	List<Object[]> getSTRTranDet(String tran_id, String tran_date, String part_tran_srl_num);

	@Query(value = "select a.cif_id,to_char(b.tran_date,'dd/MM/yyyy')tran_date,b.tran_id,b.part_tran_srl_num,b.tran_type,b.tran_sub_type,b.part_tran_type,b.tran_amt,a.ADDRESS1,a.ADDRESS2,a.nat_id_card_num,a.PREFERREDPHONE,a.CUST_NAME,a.occupation,a.placeofbirth \r\n"
			+ "from BAML_ACCT_TRANS_TABLE b,BAML_CUST_MAST_TABLE a where b.cust_id=a.cust_id and trim(b.tran_id) =?1 and b.tran_date =?2 and trim(b.part_tran_srl_num) =?3", nativeQuery = true)
	List<Object[]> getBAMLSearchIntFilter(String tran_id, String tran_date, String part_tran_srl_num);

	@Query(value = "select * from BAML_ACCT_TRANS_TABLE where trim(tran_id) =?1 and trim(part_tran_srl_num) =?2 and tran_type =?3 and part_tran_type =?4 ", nativeQuery = true)
	TRAN_MASTER reportdet(String tran_id, String part_tran_srl_num, String tran_type, String part_tran_type);

	@Query(value = "select DISTINCT a.cif_id,to_char(c.tran_date,'dd/MM/yyyy')as tran_date,c.tran_id,c.part_tran_srl_num,c.tran_type,c.tran_sub_type,c.part_tran_type,c.tran_crncy_code,c.tran_amt,c.tran_particular,c.fx_tran_amt,to_char(c.pstd_date,'dd/MM/yyyy')pstd_date,b.foracid,b.acct_name,(Case when a.cntry_code = 'MU' then 'Mauritius' else '' end) as cntry_code,a.cif_id,a.NAT_ID_CARD_NUM,a.LOCALETEXT,decode(Z.CITY_CODE,null,' ', Z.CITY_CODE) as CITY_CODE,a.PREFERREDPHONE,(Case when a.cust_sex = 'M' then 'Male' when a.cust_sex = 'F' then 'Female' else 'Other' end) as SEX,a.CUST_NAME,a.CUST_FIRST_NAME,a.CUST_LAST_NAME,(Case when a.nationality = 'MU' then 'Mauritius' else '' end) as nationality,to_char(a.cust_dob,'dd/MM/yyyy')cust_dob,decode(Y.DEPARTMENT,null,' ',Y.DEPARTMENT) as DEPARTMENT,decode(X.OCCUPATION,null,' ', X.OCCUPATION) as OCCUPATION,a.cust_short_name,c.tran_particular_code from baml_cust_mast_table a,tbaadm.gam@aml_fin b ,tbaadm.htd@aml_fin c,(select decode(r.ref_desc,null,' ',r.ref_desc) DEPARTMENT ,cust.cif_id from baml_reference_code_table r,baml_cust_mast_table cust where DECODE(cust.STRUSERFIELD10,null,'OTH',cust.STRUSERFIELD10)= r.ref_code and r.ref_rec_type='DEP' ) Y,(select decode(r1.ref_desc,null,' ',r1.ref_desc) OCCUPATION, cust1.cif_id  from baml_reference_code_table r1,baml_cust_mast_table cust1 where DECODE(cust1.occupation,null,'213',cust1.occupation)=r1.ref_code and r1.ref_rec_type='21') X,(select decode(r2.ref_desc,null,' ',r2.ref_desc) CITY_CODE,cust2.cif_id from baml_reference_code_table r2,baml_cust_mast_table cust2 where DECODE(cust2.placeofbirth,null,'999',cust2.placeofbirth)=r2.ref_code and r2.ref_rec_type='01') Z where c.ref_num = a.cif_id and c.acid = b.acid and a.cif_id = Y.CIF_ID AND a.cif_id = X.CIF_ID AND a.cif_id = Z.CIF_ID AND c.part_tran_type='C' and a.cif_id =?1  and c.tran_date =?2", nativeQuery = true)
	List<Object[]> get3rdPartyTranDetCurrent(String cust_id, String tran_date);
	
	@Query(value = "select DISTINCT a.cif_id,to_char(c.tran_date,'dd/MM/yyyy')as tran_date,c.tran_id,c.part_tran_srl_num,c.tran_type,c.tran_sub_type,c.part_tran_type,c.tran_crncy_code,c.tran_amt,c.tran_particular,c.fx_tran_amt,to_char(c.pstd_date,'dd/MM/yyyy')pstd_date,b.foracid,b.acct_name,(Case when a.cntry_code = 'MU' then 'Mauritius' else '' end) as cntry_code,a.cif_id,a.NAT_ID_CARD_NUM,a.LOCALETEXT,decode(Z.CITY_CODE,null,' ', Z.CITY_CODE) as CITY_CODE,a.PREFERREDPHONE,(Case when a.cust_sex = 'M' then 'Male' when a.cust_sex = 'F' then 'Female' else 'Other' end) as SEX,a.CUST_NAME,a.CUST_FIRST_NAME,a.CUST_LAST_NAME,(Case when a.nationality = 'MU' then 'Mauritius' else '' end) as nationality,to_char(a.cust_dob,'dd/MM/yyyy')cust_dob,decode(Y.DEPARTMENT,null,' ',Y.DEPARTMENT) as DEPARTMENT,decode(X.OCCUPATION,null,' ', X.OCCUPATION) as OCCUPATION,a.cust_short_name,c.tran_particular_code from baml_cust_mast_table a,tbaadm.gam@aml_fin b ,tbaadm.htd@aml_fin c,(select decode(r.ref_desc,null,' ',r.ref_desc) DEPARTMENT ,cust.cif_id from baml_reference_code_table r,baml_cust_mast_table cust where DECODE(cust.STRUSERFIELD10,null,'OTH',cust.STRUSERFIELD10)= r.ref_code and r.ref_rec_type='DEP' ) Y,(select decode(r1.ref_desc,null,' ',r1.ref_desc) OCCUPATION, cust1.cif_id  from baml_reference_code_table r1,baml_cust_mast_table cust1 where DECODE(cust1.occupation,null,'213',cust1.occupation)=r1.ref_code and r1.ref_rec_type='21') X,(select decode(r2.ref_desc,null,' ',r2.ref_desc) CITY_CODE,cust2.cif_id from baml_reference_code_table r2,baml_cust_mast_table cust2 where DECODE(cust2.placeofbirth,null,'999',cust2.placeofbirth)=r2.ref_code and r2.ref_rec_type='01') Z where c.ref_num = a.cif_id and c.acid = b.acid and a.cif_id = Y.CIF_ID AND a.cif_id = X.CIF_ID AND a.cif_id = Z.CIF_ID AND c.part_tran_type='C'  and c.ref_num =?1  and c.tran_date =?2", nativeQuery = true)
	List<Object[]> get3rdPartyTranDetCurrentREFNUM(String cust_id, String tran_date);
	
	@Query(value = "select DISTINCT a.cif_id,to_char(c.tran_date,'dd/MM/yyyy')as tran_date,c.tran_id,c.part_tran_srl_num,c.tran_type,c.tran_sub_type,c.part_tran_type,c.tran_crncy_code,c.tran_amt,c.tran_particular,c.fx_tran_amt,to_char(c.pstd_date,'dd/MM/yyyy')pstd_date,b.foracid,b.acct_name,(Case when a.cntry_code = 'MU' then 'Mauritius' else '' end) as cntry_code,a.cif_id,a.NAT_ID_CARD_NUM,a.LOCALETEXT,decode(Z.CITY_CODE,null,' ', Z.CITY_CODE) as CITY_CODE,a.PREFERREDPHONE,(Case when a.cust_sex = 'M' then 'Male' when a.cust_sex = 'F' then 'Female' else 'Other' end) as SEX,a.CUST_NAME,a.CUST_FIRST_NAME,a.CUST_LAST_NAME,(Case when a.nationality = 'MU' then 'Mauritius' else '' end) as nationality,to_char(a.cust_dob,'dd/MM/yyyy')cust_dob,decode(Y.DEPARTMENT,null,' ',Y.DEPARTMENT) as DEPARTMENT,decode(X.OCCUPATION,null,' ', X.OCCUPATION) as OCCUPATION,a.cust_short_name,c.tran_particular_code from baml_cust_mast_table a,tbaadm.gam@aml_fin b ,tbaadm.htd@aml_fin c,(select decode(r.ref_desc,null,' ',r.ref_desc) DEPARTMENT ,cust.cif_id from baml_reference_code_table r,baml_cust_mast_table cust where DECODE(cust.STRUSERFIELD10,null,'OTH',cust.STRUSERFIELD10)= r.ref_code and r.ref_rec_type='DEP' ) Y,(select decode(r1.ref_desc,null,' ',r1.ref_desc) OCCUPATION, cust1.cif_id  from baml_reference_code_table r1,baml_cust_mast_table cust1 where DECODE(cust1.occupation,null,'213',cust1.occupation)=r1.ref_code and r1.ref_rec_type='21') X,(select decode(r2.ref_desc,null,' ',r2.ref_desc) CITY_CODE,cust2.cif_id from baml_reference_code_table r2,baml_cust_mast_table cust2 where DECODE(cust2.placeofbirth,null,'999',cust2.placeofbirth)=r2.ref_code and r2.ref_rec_type='01') Z where c.ref_num = a.cif_id and c.acid = b.acid and a.cif_id = Y.CIF_ID AND a.cif_id = X.CIF_ID AND a.cif_id = Z.CIF_ID AND c.part_tran_type='C' and a.cif_id =?1  and c.tran_date =?2", nativeQuery = true)
	List<Object[]> get3rdPartyTranDetHistory(String cust_id, String tran_date);

	@Query(value = "select DISTINCT a.cif_id,to_char(c.tran_date,'dd/MM/yyyy')as tran_date,c.tran_id,c.part_tran_srl_num,c.tran_type,c.tran_sub_type,c.part_tran_type,c.tran_crncy_code,c.tran_amt,c.tran_particular,c.fx_tran_amt,to_char(c.pstd_date,'dd/MM/yyyy')pstd_date,b.foracid,b.acct_name,(Case when a.cntry_code = 'MU' then 'Mauritius' else '' end) as cntry_code,a.cif_id,a.NAT_ID_CARD_NUM,a.LOCALETEXT,decode(Z.CITY_CODE,null,' ', Z.CITY_CODE) as CITY_CODE,a.PREFERREDPHONE,(Case when a.cust_sex = 'M' then 'Male' when a.cust_sex = 'F' then 'Female' else 'Other' end) as SEX,a.CUST_NAME,a.CUST_FIRST_NAME,a.CUST_LAST_NAME,(Case when a.nationality = 'MU' then 'Mauritius' else '' end) as nationality,to_char(a.cust_dob,'dd/MM/yyyy')cust_dob,decode(Y.DEPARTMENT,null,' ',Y.DEPARTMENT) as DEPARTMENT,decode(X.OCCUPATION,null,' ', X.OCCUPATION) as OCCUPATION,a.cust_short_name,c.tran_particular_code from baml_cust_mast_table a,tbaadm.gam@aml_fin b ,tbaadm.htd@aml_fin c,(select decode(r.ref_desc,null,' ',r.ref_desc) DEPARTMENT ,cust.cif_id from baml_reference_code_table r,baml_cust_mast_table cust where DECODE(cust.STRUSERFIELD10,null,'OTH',cust.STRUSERFIELD10)= r.ref_code and r.ref_rec_type='DEP' ) Y,(select decode(r1.ref_desc,null,' ',r1.ref_desc) OCCUPATION, cust1.cif_id  from baml_reference_code_table r1,baml_cust_mast_table cust1 where DECODE(cust1.occupation,null,'213',cust1.occupation)=r1.ref_code and r1.ref_rec_type='21') X,(select decode(r2.ref_desc,null,' ',r2.ref_desc) CITY_CODE,cust2.cif_id from baml_reference_code_table r2,baml_cust_mast_table cust2 where DECODE(cust2.placeofbirth,null,'999',cust2.placeofbirth)=r2.ref_code and r2.ref_rec_type='01') Z where c.ref_num = a.cif_id and c.acid = b.acid and a.cif_id = Y.CIF_ID AND a.cif_id = X.CIF_ID AND a.cif_id = Z.CIF_ID AND c.part_tran_type='C'  and c.ref_num =?1  and c.tran_date =?2", nativeQuery = true)
	List<Object[]> get3rdPartyTranDetHistoryREFNUM(String cust_id, String tran_date);
	
}