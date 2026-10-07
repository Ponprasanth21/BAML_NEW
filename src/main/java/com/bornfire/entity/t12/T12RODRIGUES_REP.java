package com.bornfire.entity.t12;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T12RODRIGUES_REP extends JpaRepository<T12RODRIGUES,String> {
		 Optional<T12RODRIGUES> findById( String directorId);
		
}
