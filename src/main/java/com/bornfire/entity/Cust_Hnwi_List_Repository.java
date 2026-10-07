package com.bornfire.entity;


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
public interface Cust_Hnwi_List_Repository extends JpaRepository<Cust_Hnwi_List_Entity, String> {

	Optional<Cust_Hnwi_List_Entity> findById(String directorId);

	@Query(value = "select * from BAML_CUST_HNWI_LIST  where delflag='N'", nativeQuery = true)

	Page<Cust_Hnwi_List_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' ", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> paramlist(Pageable page);

	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' order by entity_flag, cif_id asc ", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_CUST_HNWI_LIST set DEL_FLAG ='Y' where CIF_ID =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' AND ENTITY_FLAG='Y' ", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> paramlistforReport(Pageable page);
	
	@Query(value = "select  p.cif_id,p.cust_short_name,p.cust_name,p.nat_id_card_num,p.risk_category,p.bus_desc,p.sector,p.start_date,p.active_prod_type from BAML_CUST_HNWI_LIST p ,baml_cust_mast_table c where p.nat_id_card_num=c.nat_id_card_num and p.DEL_FLAG='N' AND p.ENTITY_FLAG='Y' ", nativeQuery = true)
	List<Object[]> findAllCustIdforHNWIReport(Pageable pageable);
	
	@Query(value = "select * from BAML_CUST_HNWI_LIST where NAT_ID_CARD_NUM =?1 and foracid=?2 ", nativeQuery = true)
	List<Cust_Hnwi_List_Entity> findByforNatID(String nationaid,String foracid);
	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "UPDATE CUST_HNWI_LIST_num SET ID=(SELECT ID+1 FROM CUST_HNWI_LIST_num)",
	 * nativeQuery = true) void updatePepNumTB();
	 */
	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and cif_id like ?1% order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistBycif(Pageable page,String cif);
	
	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and UPPER(cust_short_name)  like UPPER(?1) order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistBylastname(Pageable page,String lastname);

	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and UPPER(CUST_NAME) like UPPER(?1) order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistByfirstname(Pageable page,String firstname);
	
	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and UPPER(NAT_ID_CARD_NUM) like UPPER(?1) order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistBynid(Pageable page,String nid);
	
	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and UPPER(RISK_CATEGORY) = UPPER(?1) order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistByrisk(Pageable page,String nid);
	
	@Query(value = "select * from BAML_CUST_HNWI_LIST where DEL_FLAG='N' and UPPER(ACTIVE_PROD_TYPE) = UPPER(?1) order by entity_flag, HNWI_ID asc", nativeQuery = true)
	Page<Cust_Hnwi_List_Entity> gethnwilistByprodtupe(Pageable page,String nid);
}

