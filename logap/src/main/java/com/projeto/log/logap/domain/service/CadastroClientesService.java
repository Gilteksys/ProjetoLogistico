package com.projeto.log.logap.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.log.logap.domain.exception.Exceptions;
import com.projeto.log.logap.domain.model.Clientes;
import com.projeto.log.logap.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClientesService {
	
	private ClienteRepository clienteRepository;
	
	public Clientes busca(Long clienteId) {		
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new Exceptions("Cliente não encontrado"));		
	}
	
	@Transactional
	public Clientes salvar(Clientes clientes) {
		boolean emailEmUso = clienteRepository.findByEmail(clientes.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(clientes));
		if (emailEmUso) {
			throw new Exceptions("E-mail já cadastrado");
		}		
		return clienteRepository.save(clientes);		
	}
	
	public void excluir(Long clienteById) {
		clienteRepository.deleteById(clienteById);
	}

}
