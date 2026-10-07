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
public interface Cust_Pep_List_Repository extends JpaRepository<Cust_Pep_List_Entity, String> {

	Optional<Cust_Pep_List_Entity> findById(String directorId);

	@Query(value = "select * from BAML_CUST_PEP_LIST  where delflag='N'", nativeQuery = true)

	Page<Cust_Pep_List_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' order by cif_id asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> paramlist(Pageable page);

	@Query(value = "select * from baml_cust_pep_list where  del_flag ='Y' and entity_flag ='N' OR del_flag ='N' and entity_flag ='Y' OR del_flag ='N' and entity_flag ='N' order by entity_flag, cif_id, active_prod_type asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_CUST_PEP_LIST set DEL_FLAG ='Y' where acid =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);

	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' AND ENTITY_FLAG='Y' ", nativeQuery = true)
	Page<Cust_Pep_List_Entity> paramlistforReport(Pageable page);
	

	@Query(value = "select * from BAML_CUST_PEP_LIST where NAT_ID_CARD_NUM =?1 OR CUST_NAME=?2 OR CIF_ID = ?3", nativeQuery = true)
	Cust_Pep_List_Entity getPepListByNID_FirstName(String nat_id_num,String firstname,String cif_id);
	
	@Query(value = "select  p.cif_id,p.cust_short_name,p.cust_name,c.occupation,p.nat_id_card_num,p.risk_category,p.pep_desc,p.cust_position,p.membership_date,p.date_of_pep,p.date_of_resig,p.resig_reasons,p.active_prod_type,p.foracid,p.acid from BAML_CUST_PEP_LIST p ,baml_cust_mast_table c where p.nat_id_card_num=c.nat_id_card_num and p.DEL_FLAG='N' AND p.ENTITY_FLAG='Y' order by p.cif_id, p.active_prod_type asc", nativeQuery = true)
	List<Object[]> findAllCustIdforPEPReport();
	
	@Query(value = "select * from BAML_CUST_PEP_LIST where foracid =?1 ", nativeQuery = true)
	List<Cust_Pep_List_Entity> findByforacid(String foracicd);
	
	
	@Query(value = "select acid from baml_acct_mast_table where foracid =?1 ", nativeQuery = true)
	String findByforacidacc(String foracicd);
	
//	@Modifying
//	@Query(value = "UPDATE CUST_PEP_LIST_num SET ID=(SELECT ID+1 FROM CUST_PEP_LIST_num)", nativeQuery = true)
//	void updatePepNumTB();
	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' and cif_id like %?1% order by entity_flag asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> getpeplistBycif(String cif,Pageable page);
	
	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' and UPPER(cust_short_name)  like UPPER(?1) order by entity_flag asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> getpeplistBylastname(String lastname,Pageable page);

	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' and UPPER(cust_name) like UPPER(?1) order by entity_flag asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> getpeplistByfirstname(String firstname,Pageable page);
	
	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' and UPPER(nat_id_card_num) like UPPER(?1) order by entity_flag asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> getpeplistBynid(String nid,Pageable page);

	@Query(value = "select * from BAML_CUST_PEP_LIST where DEL_FLAG='N' and UPPER(RISK_CATEGORY) = UPPER(?1) order by entity_flag asc", nativeQuery = true)
	Page<Cust_Pep_List_Entity> getIndlistByrisk(String risk,Pageable page);
}

