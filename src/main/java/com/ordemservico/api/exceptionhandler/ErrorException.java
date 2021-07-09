package com.ordemservico.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorException {

	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<MensagemCampo> mensagemCampos;
	
		
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
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
