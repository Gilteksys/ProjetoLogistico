package com.projeto.log.logap.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.log.logap.domain.exception.Exceptions;
import com.projeto.log.logap.domain.model.Entrega;
import com.projeto.log.logap.domain.model.Ocorrencia;
import com.projeto.log.logap.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Ocorrencia registrar (Long entregaId, String descricao) {
		Entrega entrega = entregaRepository.findById(entregaId)
				.orElseThrow(() -> new Exceptions("Entrega não encontrada"));
		
		return entrega.adicionarOcorrencia(descricao);				

	}

}
