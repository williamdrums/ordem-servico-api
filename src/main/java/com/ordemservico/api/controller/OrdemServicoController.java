package com.ordemservico.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ordemservico.domain.model.OrdemServico;
import com.ordemservico.domain.repository.ClienteRepository;
import com.ordemservico.domain.repository.OrdemServicoRepository;
import com.ordemservico.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;

	@Autowired
	public OrdemServicoRepository ordemServicoRepository;


	@GetMapping
	public List<OrdemServico> findAll(){
		return ordemServicoRepository.findAll();
	}

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> findById(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico =  ordemServicoRepository.findById(ordemServicoId);

		if(ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServico.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico create(@Valid @RequestBody OrdemServico  ordemServico) {
		return gestaoOrdemServicoService.create(ordemServico);
	}

	@PutMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> update(
			@Valid @PathVariable Long ordemServicoId
			,@RequestBody OrdemServico ordemServico){
		
		if(ordemServico.getId() != ordemServicoId) {
			return ResponseEntity.badRequest().build();
		}
		
		if(!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}
		
		ordemServico.setId(ordemServicoId);
		gestaoOrdemServicoService.create(ordemServico);

		return ResponseEntity.ok(ordemServico);

	}
	
	@DeleteMapping("/{ordemServicoId}")
	public ResponseEntity<Void> delete(@PathVariable Long ordemServicoId){
		if(!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}
		gestaoOrdemServicoService.delete(ordemServicoId);
		
		return ResponseEntity.noContent().build();
	}
}
