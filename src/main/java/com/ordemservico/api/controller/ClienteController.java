package com.ordemservico.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ordemservico.api.dto.ClienteDTO;
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

import com.ordemservico.domain.model.Cliente;
import com.ordemservico.domain.repository.ClienteRepository;
import com.ordemservico.domain.service.CadastroClienteService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroCLienteService;

    @GetMapping
    public List<ClienteDTO> findByAll() {
        return toCollectionModelDto(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            ClienteDTO clienteDTO = modelToDto(cliente.get());
            return ResponseEntity.ok(clienteDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO create(@Valid @RequestBody Cliente cliente) {
        return modelToDto(cadastroCLienteService.create(cliente));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateClient(
            @Valid
            @PathVariable Long clienteId,
            @RequestBody Cliente cliente) {

        if (cliente.getId() != clienteId) {
            return ResponseEntity.badRequest().build();
        }

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cadastroCLienteService.create(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cadastroCLienteService.delete(clienteId);

        return ResponseEntity.noContent().build();
    }

    private ClienteDTO modelToDto(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }
    
    private List<ClienteDTO> toCollectionModelDto(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> modelToDto(cliente))
                .collect(Collectors.toList());
    }

}
