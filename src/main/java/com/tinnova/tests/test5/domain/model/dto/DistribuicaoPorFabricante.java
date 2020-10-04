package com.tinnova.tests.test5.domain.model.dto;

import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;

public class DistribuicaoPorFabricante {
	
	private MarcasEnum fabricante;
	private Long quantidade;
	
	
	public DistribuicaoPorFabricante(MarcasEnum fabricante, Long quantidade) {
		this.fabricante = fabricante;
		this.quantidade = quantidade;
	}
	
	public MarcasEnum getFabricante() {
		return fabricante;
	}
	public void setFabricante(MarcasEnum fabricante) {
		this.fabricante = fabricante;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
