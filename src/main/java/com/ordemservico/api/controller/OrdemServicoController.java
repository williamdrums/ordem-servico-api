package com.ordemservico.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ordemservico.api.dto.OrdemServicoDTO;
import com.ordemservico.domain.model.OrdemServico;
import com.ordemservico.domain.repository.ClienteRepository;
import com.ordemservico.domain.repository.OrdemServicoRepository;
import com.ordemservico.domain.service.GestaoOrdemServicoService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;

	@Autowired
	public OrdemServicoRepository ordemServicoRepository;

	@Autowired
	public ModelMapper modelMapper; //converte dados de entidade para dto


	@GetMapping
	public List<OrdemServicoDTO> findAll(){
		return toCollectionModelDTO(ordemServicoRepository.findAll());
	}

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico =  ordemServicoRepository.findById(ordemServicoId);

		if(ordemServico.isPresent()) {

			OrdemServicoDTO  ordemServicoDTO = converteModelParaDTO(ordemServico.get());
			return ResponseEntity.ok(ordemServicoDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO create(@Valid @RequestBody OrdemServico  ordemServico) {
		return converteModelParaDTO(gestaoOrdemServicoService.create(ordemServico));
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

	private OrdemServicoDTO converteModelParaDTO(OrdemServico ordemServico) {
		//converte os dados de ordemServico para OrdemServicoDTO
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}

	private List<OrdemServicoDTO> toCollectionModelDTO(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> converteModelParaDTO(ordemServico))
				.collect(Collectors.toList());
	}
}
