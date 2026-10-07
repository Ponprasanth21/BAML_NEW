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
public interface  AccessandRolesRepository extends JpaRepository<AMLAccessRole,String> {
	 Optional<AMLAccessRole> findById( String directorId);
	 
	 
	 @Query(value = "select * from BAML_ACCESS_ROLE_TABLE  where ROLE_ID =?1", nativeQuery = true)
		String FindByAll(String roleId);

	 
	 @Query(value = "select * from BAML_ACCESS_ROLE_TABLE  where DEL_FLG='N'", nativeQuery = true)
		Page<AMLAccessRole> rulelist(Pageable page);
	 
	 
	 @Modifying
		@Query(value = "UPDATE BAML_ACCESS_ROLE_TABLE set DEL_FLG ='Y' where ROLE_ID =?1", nativeQuery = true)
		String findByfgdg1(String roleId);
	 
	 @Query(value = "select distinct ROLE_ID from BAML_ACCESS_ROLE_TABLE  where DEL_FLG='N' and entity_flg ='Y' ", nativeQuery = true)
		List<String> roleidtype();

}
