package com.projeto.log.logap.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.log.logap.domain.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{
	
	
	

}
