package com.projeto.log.logap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.logap.domain.model.Clientes;
import com.projeto.log.logap.domain.repository.ClienteRepository;
import com.projeto.log.logap.domain.service.CadastroClientesService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
		
	private ClienteRepository clienteRepository;	
	private CadastroClientesService cadastroClientesService; 
	
	@GetMapping
	public List <Clientes> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clientesId}")
	public ResponseEntity<Clientes> procurar(@PathVariable Long clientesId) {		
		return clienteRepository.findById(clientesId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());		
	}	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Clientes adcionar(@Valid @RequestBody Clientes clientes) {
		return cadastroClientesService.salvar(clientes);		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Clientes> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody Clientes clientes){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clientes.setId(clienteId);
		clientes = cadastroClientesService.salvar(clientes);
		
		return ResponseEntity.ok(clientes);
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir (@PathVariable Long clienteId){
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
	    }
		
		cadastroClientesService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();		
	}		
}	


