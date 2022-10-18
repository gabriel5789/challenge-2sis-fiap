package com.fiap.crm.controller;

import com.fiap.crm.auth.ApplicationUser;
import com.fiap.crm.model.Cliente;
import com.fiap.crm.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		Cliente cliente = repository.findById(id).orElse(null);
		if(cliente != null && userDetails.getPrincipal().equals(cliente.getEmail())) {
			return cliente;
		}
		return null;
	};

}
