package com.tinnova.tests.test5.domain.model.dto;

public class DistribuicaoPorDecada {
	
	private Integer decada;
	private Long quantidade;
	
	public DistribuicaoPorDecada(Integer decada, Long quantidade) {
		this.decada = decada;
		this.quantidade = quantidade;
	}
	
	public Integer getDecada() {
		return decada;
	}
	public void setDecada(Integer decada) {
		this.decada = decada;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
