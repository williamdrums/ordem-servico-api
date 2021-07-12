package com.ordemservico.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ErrorException {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<MensagemCampo> mensagemCampos;
	
		
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<MensagemCampo> getCampos() {
		return mensagemCampos;
	}
	public void setCampos(List<MensagemCampo> mensagemCampos) {
		this.mensagemCampos = mensagemCampos;
	}
	
		
}
