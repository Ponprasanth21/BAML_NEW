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
public interface AlertManagementRepository extends JpaRepository<AlertManagementEntity, String> {

	Optional<AlertManagementEntity> findById(String directorId);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"DEL_FLG\" = 'N' ORDER BY \"SRL_NO\"",
			countQuery = "SELECT COUNT(*) FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"DEL_FLG\" = 'N'",
			nativeQuery = true)
	Page<AlertManagementEntity> alertlist(Pageable page);

	@Modifying
	@Query(value = "UPDATE \"AML\".\"BAML_ALERT_MGMT_TABLE\" SET \"DEL_FLG\" = 'Y' WHERE \"SRL_NO\" = ?1", nativeQuery = true)
	int findByfgdg1(String srl_no);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"ALERT_CODE\" = ?1", nativeQuery = true)
	AlertManagementEntity getalertdetail(String alertcode);

	@Query(value = "SELECT * FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"SRL_NO\" = ?1", nativeQuery = true)
	AlertManagementEntity findByIdcustom(String srlNo);
	
	@Query(value = "SELECT \"PARAM_1\" FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"SRL_NO\" = ?1", nativeQuery = true)
	String getParamdetail1(String srl_no);
	
	@Query(value = "SELECT \"PARAM_2\" FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"SRL_NO\" = ?1", nativeQuery = true)
	String getParamdetail2(String srl_no);
	
	@Query(value = "SELECT \"PARAM_3\" FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\" WHERE \"SRL_NO\" = ?1", nativeQuery = true)
	String getParamdetail3(String srl_no);
	
	@Query(value = "SELECT * FROM \"AML\".\"BAML_USER_ALERT_NOTIFICATION_TABLE\"", nativeQuery = true)
	List<AMLUserAlertNotification> getUserList();

}
