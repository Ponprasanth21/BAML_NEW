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
public interface RuleEngineRepository extends JpaRepository<RuleEngineEntity, String> {

	Optional<RuleEngineEntity> findById(String directorId);
	
	@Query(value = "select * from RULE_ENGINE  where del_flg='N'", nativeQuery = true)
	Page<RuleEngineEntity> rulelist(Pageable page);

	@Modifying
	@Query(value = "UPDATE RULE_ENGINE u set u.DEL_FLG ='Y' where u.SRL_NO =?1", nativeQuery = true)
	int findByfgdg1(String srl_no);
	
	@Query(value = "select * from RULE_ENGINE", nativeQuery = true)
	List<RuleEngineEntity> findAllCustom();


	@Query(value = "select * from RULE_ENGINE where RULE_CODE=?1", nativeQuery = true)
	List<RuleEngineEntity> getRuleCodeData(String id);
	
	@Query(value = "select RULE_DESCRIPTION from RULE_ENGINE where RULE_CODE=?1", nativeQuery = true)
	String getDescription(String id);

}