package com.ordemservico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordemservico.domain.exception.NegocioException;
import com.ordemservico.domain.model.Cliente;
import com.ordemservico.domain.repository.ClienteRepository;


@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente create(Cliente cliente) {

		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		}
		return clienteRepository.save(cliente);
	}

	public void delete(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
 