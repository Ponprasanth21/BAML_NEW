package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface EMAILREP extends JpaRepository<EmailAlert, String> {

	
	@Query(value = "select count(*) from BAML_EMAIL_NOTIFICATION_TABLE where send_flg ='N'", nativeQuery = true)
	int getEmailSentCount();
	
	
	@Query(value = "SELECT * FROM BAML_EMAIL_NOTIFICATION_TABLE where send_flg='N'", nativeQuery = true)
	List<EmailAlert> getEmailDetails();
	
	@Query(value = "SELECT * FROM BAML_EMAIL_NOTIFICATION_TABLE where email_srl_no = ?1", nativeQuery = true)
	EmailAlert getEmailbySRl(BigDecimal srl);
	
	@Query(value = "SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL", nativeQuery = true)
	BigDecimal gettestsrlno();
	
	@Query(value = "SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL", nativeQuery = true)
	BigDecimal getsequence();
}
