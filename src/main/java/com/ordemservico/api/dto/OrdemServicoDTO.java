package com.ordemservico.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ordemservico.domain.model.StatusOrdemServico;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrdemServicoDTO {

	private Long id;
	private String nomeCliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
}
