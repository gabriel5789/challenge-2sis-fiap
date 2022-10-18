package com.fiap.crm.controller;

import com.fiap.crm.model.Cliente;
import com.fiap.crm.model.Endereco;
import com.fiap.crm.repository.CidadeRepository;
import com.fiap.crm.repository.ClienteRepository;
import com.fiap.crm.repository.EnderecoRepository;
import com.fiap.crm.validation.Put;
import com.fiap.crm.validation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@RestController
@RestResource(path = "/api/private/cliente")
@RequestMapping("api/private/cliente")
public class ClientePrivateController {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("{id}")
	public Cliente getCliente(@PathVariable Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@GetMapping
	public List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Cliente cadastrarClientes(@RequestBody @Validated(Post.class) Cliente cliente) {
		if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()
			|| clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
			System.out.println("409 CONFLICT"); // TODO: Tratar a exceção e retornar o HTTP Status adequado
			return null;
		}
		List<Endereco> enderecos = cliente.getEnderecos();
		cliente.setEnderecos(new LinkedList<>());
		Cliente retorno = clienteRepository.saveAndFlush(cliente);
		retorno.setEnderecos(enderecos);
		for (Endereco endereco : enderecos) {
			endereco.setClienteId(retorno.getId());
			cidadeRepository.save(endereco.getCidade());
			enderecoRepository.save(endereco);
		}
		return retorno;
	}

	@PutMapping
	@Transactional
	public Cliente atualizarCliente(@RequestBody @Validated(Put.class) Cliente cliente) {
		if (clienteRepository.existsById(cliente.getId())) {
			enderecoRepository.deleteAllByClienteId(cliente.getId());
			enderecoRepository.flush();
			List<Endereco> enderecos = cliente.getEnderecos();
			cliente.setEnderecos(new LinkedList<>());
			Cliente retorno = clienteRepository.save(cliente);
			retorno.setEnderecos(enderecos);
			for (Endereco endereco : enderecos) {
				endereco.setClienteId(retorno.getId());
				enderecoRepository.save(endereco);
			}
			return retorno;
		} else
			return cadastrarClientes(cliente);
	}

	@DeleteMapping(path = "{id}")
	@Transactional
	public void deletarCliente(@PathVariable Long id) {
		enderecoRepository.deleteAllByClienteId(id);
		enderecoRepository.flush();
		clienteRepository.deleteById(id);
	}
}
