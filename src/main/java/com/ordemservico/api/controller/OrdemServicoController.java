package com.ordemservico.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ordemservico.domain.model.OrdemServico;
import com.ordemservico.domain.repository.OrdemServicoRepository;
import com.ordemservico.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	public OrdemServicoRepository ordemServicoRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico create(@Valid @RequestBody OrdemServico  ordemServico) {
		return gestaoOrdemServicoService.create(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServico> findAll(){
		
		return ordemServicoRepository.findAll();
	}
}
