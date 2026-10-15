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
	 
	 
	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCESS_ROLE_TABLE\" WHERE \"ROLE_ID\" = ?1", nativeQuery = true)
	String FindByAll(String roleId);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ACCESS_ROLE_TABLE\" WHERE \"DEL_FLG\" = 'N' ORDER BY \"ROLE_ID\"",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_ACCESS_ROLE_TABLE\" WHERE \"DEL_FLG\" = 'N'",
			nativeQuery = true)
	Page<AMLAccessRole> rulelist(Pageable page);

	@Modifying
	@Query(value = "UPDATE \"AML\".\"BAML_ACCESS_ROLE_TABLE\" SET \"DEL_FLG\" = 'Y' WHERE \"ROLE_ID\" = ?1", nativeQuery = true)
	String findByfgdg1(String roleId);

	@Query(value = "SELECT DISTINCT \"ROLE_ID\" FROM \"AML\".\"BAML_ACCESS_ROLE_TABLE\" WHERE \"DEL_FLG\" = 'N' AND \"ENTITY_FLG\" = 'Y'", nativeQuery = true)
	List<String> roleidtype();
}
