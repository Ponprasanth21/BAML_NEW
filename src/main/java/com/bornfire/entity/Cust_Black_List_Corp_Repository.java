package com.bornfire.entity;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Cust_Black_List_Corp_Repository extends JpaRepository<Cust_Black_List_Corp_Entity, BigDecimal> {

	Optional<Cust_Black_List_Corp_Entity> findById(BigDecimal directorId);

	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR  where delflag='N'", nativeQuery = true)

	Page<Cust_Black_List_Corp_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' ", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> paramlist(Pageable page);

	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' order by entity_flag,black_list_corp_data_setid ASC", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_CUST_BLACK_LIST_COR set DEL_FLAG ='Y' where uniqueidnumber =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR  where del_flg='N'", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> rulelist(Pageable page);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' AND ENTITY_FLAG='Y'", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> parameterlistfor_Report(Pageable page);
	
	@Modifying
	@Query(value = "UPDATE CUST_BLACK_LIST_CORP_NUM SET ID=(SELECT ID+1 FROM CUST_BLACK_LIST_CORP_NUM)", nativeQuery = true)
	void updateCust_balck_List_Corp_NumTB();


	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where NATIONALID =?1 OR FIRSTNAME=?2 OR CIFID = ?3", nativeQuery = true)
	Cust_Black_List_Corp_Entity findBy_national_Id_FirstName(String nat_id,String firstname,String cif_id);
	
	@Query(value = "select p.BLACK_LIST_CORP_DATA_SETID,p.CIFID,p.SHORTNAME,p.FIRSTNAME,p.NATIONALID,p.RISK_CATEGORY,p.SECTOR,p.STATUS,p.BLACK_LIST_REASON_NOTES,p.DATE_FREEZED,p.DATE_DEFREEZED,p.ACTIVE_PRODUCT_TYPE from cust_black_list_cor p ,baml_cust_mast_table c where p.NATIONALID=c.nat_id_card_num and p.DEL_FLAG='N' AND p.ENTITY_FLAG='Y' ", nativeQuery = true)
	List<Object[]> findAllCustIdforBlacklistCorpReport(Pageable pageable);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where NATIONALID =?1 ", nativeQuery = true)
	List<Cust_Black_List_Corp_Entity> findByforNatID(String nationaid);
	
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and cifid like ?1% order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getCorplistBycif(Pageable page,String cif);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and UPPER(shortname_alt1)  like UPPER(?1) order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getCorplistBylastname(Pageable page,String lastname);

	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and UPPER(firstname) like UPPER(?1) order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getCorplistByfirstname(Pageable page,String firstname);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and UPPER(idtyper1) like UPPER(?1) order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getCorplistBynid(Pageable page,String nid);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and UPPER(RISK_CATEGORY) = UPPER(?1) order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistByrisk(Pageable page,String risk);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and MASTERDATAID = ?1 order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistBymasterdataid(Pageable page,BigDecimal masterdataid);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and userfield4 = ?1 or  userfield4 is null order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistBystatusOpenedANDEmpty(Pageable page,String status);

	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and userfield4 = ?1 order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistBystatus(Pageable page,String status);
	
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and DATE_FREEZED = ?1 order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistByDATE_FREEZED(Pageable page,Date status);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and DATE_DEFREEZED = ?1 order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistByDATE_DeFREEZED(Pageable page,Date status);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' and ACTIVE_PRODUCT_TYPE = ?1 order by entity_flag, black_list_corp_data_setid asc", nativeQuery = true)
	Page<Cust_Black_List_Corp_Entity> getIndlistByProdType(Pageable page,String status);
	
	@Query(value = "select * from BAML_CUST_BLACK_LIST_COR where DEL_FLAG='N' ", nativeQuery = true)
	List<Cust_Black_List_Corp_Entity> paramlist();
}

