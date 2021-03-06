package com.projeto.log.logap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.logap.assembler.EntregaAssembler;
import com.projeto.log.logap.domain.model.Entrega;
import com.projeto.log.logap.domain.repository.EntregaRepository;
import com.projeto.log.logap.domain.service.FinalizacaoEntregaService;
import com.projeto.log.logap.domain.service.SolicitacaoDeEntregaService;
import com.projeto.log.logap.model.EntregaModel;
import com.projeto.log.logap.model.input.EntregaInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoDeEntregaService solicitacaoDeEntregaService;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {			
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);		
		Entrega entregaSolicitada = solicitacaoDeEntregaService.solicitar(novaEntrega);
		
		return entregaAssembler.toModel(entregaSolicitada);				
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar (@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
		
		
		
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> busca(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());				
	}			
}
