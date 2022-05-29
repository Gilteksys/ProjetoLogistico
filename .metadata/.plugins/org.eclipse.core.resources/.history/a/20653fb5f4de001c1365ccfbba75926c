package com.projeto.log.logap.domain.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.log.logap.domain.model.Clientes;
import com.projeto.log.logap.domain.model.Entrega;
import com.projeto.log.logap.domain.model.StatusEntrega;
import com.projeto.log.logap.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoDeEntregaService {
	
	private CadastroClientesService cadastroClientesService;
	private EntregaRepository entregaRepositore;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Clientes clientes = cadastroClientesService.busca(entrega.getClientes().getId());
		
		entrega.setClientes(clientes);	
		entrega.setStatus(StatusEntrega.PENDENTE); 
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepositore.save(entrega);
		
	}
	

}
