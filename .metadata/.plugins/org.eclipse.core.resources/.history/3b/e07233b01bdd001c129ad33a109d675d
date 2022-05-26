package com.projeto.log.logap.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.log.logap.domain.model.Clientes;

@RestController
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/clientes")
	public List <Clientes> listar() {
		return manager.createQuery("from Clientes",Clientes.class)
				.getResultList();
					
		}	

}
