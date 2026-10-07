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
public interface NegativeListRepository extends JpaRepository<NegativeListEntity, BigDecimal> {

	Optional<NegativeListEntity> findById(BigDecimal directorId);

	@Query(value = "select * from BAML_NEGATIVE_LIST  where delflag='N'", nativeQuery = true)

	Page<NegativeListEntity> findAllRandom(Pageable pageable);

	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' ", nativeQuery = true)
	Page<NegativeListEntity> paramlist(Pageable page);

	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> parameterlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE BAML_NEGATIVE_LIST set DEL_FLAG ='Y' where uniqueidnumber =?1", nativeQuery = true)
	String findByfgdg1(String negativelistdatasetid);


	@Query(value = "select * from BAML_NEGATIVE_LIST  where del_flg='N'", nativeQuery = true)
	Page<NegativeListEntity> rulelist(Pageable page);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' AND ENTITY_FLAG='Y' ", nativeQuery = true)
	Page<NegativeListEntity> paramlistforReport(Pageable page);
	
	@Modifying
	@Query(value = "UPDATE negative_list_num SET ID=(SELECT ID+1 FROM negative_list_num)", nativeQuery = true)
	void updateNegListNumTB();

	@Query(value = "select  p.NEGATIVELISTDATASETID,p.CIFID,p.LASTNAME,p.FIRSTNAME,p.NATIONALID,p.RISK_CATEGORY,p.STATUS,p.NEGATIVELISTREASONNOTES,p.DATE_FREEZED,p.DATE_DEFREEZED,p.ACTIVE_PRODUCT_TYPE from negative_list p ,baml_cust_mast_table c where p.NATIONALID=c.nat_id_card_num and p.DEL_FLAG='N' AND p.ENTITY_FLAG='Y' ", nativeQuery = true)
	List<Object[]> findAllCustIdforNegativeReport(Pageable pageable);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where NATIONALID =?1 ", nativeQuery = true)
	List<NegativeListEntity> findByforNatID(String nationaid);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and cifid like ?1% order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getNeglistBycif(Pageable page,String cif);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and UPPER(lastname)  like UPPER(?1) order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getNeglistBylastname(Pageable page,String lastname);

	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and UPPER(firstname) like UPPER(?1) order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getNeglistByfirstname(Pageable page,String firstname);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and UPPER(NATIONALID) like UPPER(?1) order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getNeglistBynid(Pageable page,String nid);
	
	
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and UPPER(RISK_CATEGORY) = UPPER(?1) order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistByrisk(Pageable page,String risk);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and MASTERDATAID = ?1 order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistBymasterdataid(Pageable page,BigDecimal masterdataid);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and userfield4 = ?1 or  userfield4 is null order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistBystatusOpenedANDEmpty(Pageable page,String status);

	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and userfield4 = ?1 order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistBystatus(Pageable page,String status);
	
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and DATE_FREEZED = ?1 order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistByDATE_FREEZED(Pageable page,Date status);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and DATE_DEFREEZED = ?1 order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistByDATE_DeFREEZED(Pageable page,Date status);
	
	@Query(value = "select * from BAML_NEGATIVE_LIST where DEL_FLAG='N' and ACTIVE_PRODUCT_TYPE = ?1 order by entity_flag, negativelistdatasetid asc", nativeQuery = true)
	Page<NegativeListEntity> getIndlistByProdType(Pageable page,String status);
}

