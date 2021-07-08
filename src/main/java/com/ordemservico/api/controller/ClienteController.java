package com.ordemservico.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordemservico.domain.model.Cliente;
import com.ordemservico.domain.repository.ClienteRepository;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll() ;
	}
	
	@PostMapping
	public Cliente cadastrar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
