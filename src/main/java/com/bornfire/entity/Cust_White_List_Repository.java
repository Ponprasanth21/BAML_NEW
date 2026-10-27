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
public interface Cust_White_List_Repository extends JpaRepository<Cust_White_List_Entity, BigDecimal> {

	Optional<Cust_White_List_Entity> findById(BigDecimal directorId);

	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\"  where \"DEL_FLAG\"='N'", nativeQuery = true)
	Page<Cust_White_List_Entity> findAllRandom(Pageable pageable);

	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' ", nativeQuery = true)
	Page<Cust_White_List_Entity> paramlist(Pageable page);

	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE \"AML\".\"BAML_CUST_WHITE_LIST\" set \"DEL_FLAG\" ='Y' where \"UNIQUEIDNUMBER\" =?1", nativeQuery = true)
	String findByfgdg1(String cust_white_list_id);


	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\"  where \"DEL_FLAG\"='N'", nativeQuery = true)
	Page<Cust_White_List_Entity> rulelist(Pageable page);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' AND \"ENTITY_FLAG\"='Y' ", nativeQuery = true)
	Page<Cust_White_List_Entity> paramlistforReport(Pageable page);
	
	@Modifying
	@Query(value = "UPDATE \"AML\".\"negative_list_num\" SET ID=(SELECT ID+1 FROM \"AML\".\"negative_list_num\")", nativeQuery = true)
	void updateNegListNumTB();

	@Query(value = "select  p.\"NEGATIVELISTDATASETID\",p.\"CIFID\",p.\"LASTNAME\",p.\"FIRSTNAME\",p.\"NATIONALID\",p.\"RISK_CATEGORY\",p.\"STATUS\",p.\"NEGATIVELISTREASONNOTES\",p.\"DATE_FREEZED\",p.\"DATE_DEFREEZED\",p.\"ACTIVE_PRODUCT_TYPE\" from \"AML\".\"BAML_NEGATIVE_LIST\" p ,\"AML\".\"BAML_CUST_MAST_TABLE\" c where p.\"NATIONALID\"=c.\"NAT_ID_CARD_NUM\" and p.\"DEL_FLAG\"='N' AND p.\"ENTITY_FLAG\"='Y' ", nativeQuery = true)
	List<Object[]> findAllCustIdforNegativeReport(Pageable pageable);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"NATIONALID\" =?1 ", nativeQuery = true)
	List<Cust_White_List_Entity> findByforNatID(String nationaid);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"CIFID\" like ?1% order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getNeglistBycif(Pageable page,String cif);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"LASTNAME\")  like UPPER(?1) order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getNeglistBylastname(Pageable page,String lastname);

	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"FIRSTNAME\") like UPPER(?1) order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getNeglistByfirstname(Pageable page,String firstname);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"NATIONALID\") like UPPER(?1) order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getNeglistBynid(Pageable page,String nid);
	
	
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and UPPER(\"RISK_CATEGORY\") = UPPER(?1) order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistByrisk(Pageable page,String risk);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"MASTERDATAID\" = ?1 order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistBymasterdataid(Pageable page,BigDecimal masterdataid);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and (\"USERFIELD4\" = ?1 or \"USERFIELD4\" is null) order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistBystatusOpenedANDEmpty(Pageable page,String status);

	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"USERFIELD4\" = ?1 order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistBystatus(Pageable page,String status);
	
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"DATE_FREEZED\" = ?1 order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistByDATE_FREEZED(Pageable page,Date status);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"DATE_DEFREEZED\" = ?1 order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistByDATE_DeFREEZED(Pageable page,Date status);
	
	@Query(value = "select * from \"AML\".\"BAML_CUST_WHITE_LIST\" where \"DEL_FLAG\"='N' and \"ACTIVE_PRODUCT_TYPE\" = ?1 order by \"ENTITY_FLAG\", \"CUST_WHITE_LIST_ID\" asc", nativeQuery = true)
	Page<Cust_White_List_Entity> getIndlistByProdType(Pageable page,String status);
}

