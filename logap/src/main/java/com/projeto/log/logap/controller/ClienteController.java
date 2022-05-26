package com.projeto.log.logap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.logap.domain.model.Clientes;
import com.projeto.log.logap.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
public class ClienteController {
		
	private ClienteRepository clienteRepository;	
	
	@GetMapping("/clientes")
	public List <Clientes> listar() {
		
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{clientesId}")
	public ResponseEntity<Clientes> procurar(@PathVariable Long clientesId) {
		
		Optional<Clientes> clientes = clienteRepository.findById(clientesId);
		
		if (clientes.isPresent()) {
			return ResponseEntity.ok(clientes.get());
		}
		
		return ResponseEntity.notFound().build();
	}
		

}
