package com.bornfire.entity.t18;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface T18ReportRepo extends JpaRepository <T18Report,Date> {
	Optional<T18Report> findById(Date directorId);

	
	@Query(value = "SELECT C11F_TOTAL_NOF_TRANS FROM T18DIST_CHANNELS_TABLE  WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal35(String report_date);

	@Query(value = "SELECT C11G_TOTAL_VAL_TRANS FROM T18DIST_CHANNELS_TABLE WHERE REPORT_DATE=?1", nativeQuery = true)
	long findVal36(String report_date);
}
