package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface AMLUserAlertReposirtory extends JpaRepository<AMLUserAlertNotification, BigDecimal> {

	Optional<AMLUserAlertNotification> findById(BigDecimal directorId);

	
	@Query(value = "select * from BAML_USER_ALERT_NOTIFICATION_TABLE order by user_alert_srl_no  ", nativeQuery = true)
	List<AMLUserAlertNotification> getUserList();
	
	@Query(value = "select * from BAML_USER_ALERT_NOTIFICATION_TABLE ", nativeQuery = true)
	List<AMLUserAlertNotification> getUsersubList();
	
	@Query(value = "select * from BAML_USER_ALERT_NOTIFICATION_TABLE where alert_type='I'", nativeQuery = true)
	List<AMLUserAlertNotification> getUsersubIList();
	
	
	@Query(value = "select count(user_alert_body) from BAML_USER_ALERT_NOTIFICATION_TABLE ", nativeQuery = true)
	long getAlertCount();
	
	
	@Query(value = "select count(user_alert_body) from BAML_USER_ALERT_NOTIFICATION_TABLE where alert_type='I'", nativeQuery = true)
	long getAlertCount1();
	
	@Query(value = "select * from BAML_USER_ALERT_NOTIFICATION_TABLE where  trunc(user_alert_date)=?1 and user_alert_date is not null order by user_alert_date desc ", nativeQuery = true)
	List<AMLUserAlertNotification> findAllCustomAlert(String user_alert_date);
	
	
	@Query(value = "select * from BAML_USER_ALERT_NOTIFICATION_TABLE where  trunc(user_alert_date)=?1 and  ALERT_TYPE='I' ", nativeQuery = true)
	List<AMLUserAlertNotification> findAllCustomIAlert(String user_alert_date);
}
