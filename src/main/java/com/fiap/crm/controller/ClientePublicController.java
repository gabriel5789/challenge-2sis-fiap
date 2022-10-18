package com.fiap.crm.controller;

import com.fiap.crm.model.Cliente;
import com.fiap.crm.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestResource(path = "/api/public/cliente")
@RequestMapping(path = "api/public/cliente")
public class ClientePublicController {
	@Autowired
	private ClienteRepository repository;

	@GetMapping("{id}")
	public Cliente getCliente(@PathVariable Long id) {
		return repository.findById(id).orElse(null);
	};

}
