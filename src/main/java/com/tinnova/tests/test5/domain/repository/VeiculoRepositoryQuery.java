package com.tinnova.tests.test5.domain.repository;

import java.util.List;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorFabricante;

public interface VeiculoRepositoryQuery {
	
	public List<Veiculo> find(String veiculo, String marca, Integer ano, String descricao, Boolean vendido);
		
	public List<DistribuicaoPorFabricante> buscaPorFabricante();
	

	


}
