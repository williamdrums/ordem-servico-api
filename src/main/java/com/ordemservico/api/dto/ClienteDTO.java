package com.ordemservico.api.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
}
