package com.bornfire.entity.t6;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface T6ReportsRep extends JpaRepository<T6Report, Date>{

	Optional<T6Report> findById(Date directorId);
}
