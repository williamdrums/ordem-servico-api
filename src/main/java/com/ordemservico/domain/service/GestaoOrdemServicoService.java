package com.ordemservico.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordemservico.domain.exception.NegocioException;
import com.ordemservico.domain.model.Cliente;
import com.ordemservico.domain.model.OrdemServico;
import com.ordemservico.domain.model.StatusOrdemServico;
import com.ordemservico.domain.repository.ClienteRepository;
import com.ordemservico.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemServico create(OrdemServico ordemServico) {

		Cliente cliente =  clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return ordemServicoRepository.save(ordemServico);
	}
}
