package com.tinnova.tests.test5.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorDecada;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQuery{

	List<Veiculo> findByVendidoFalse();
	
	@Query("SELECT new com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorDecada(v.decada, COUNT(*)) FROM Veiculo AS v GROUP BY v.decada")
	List<DistribuicaoPorDecada> buscaPorDecada();
	
	@Query("SELECT v FROM Veiculo v WHERE v.created >= :fim")
	public List<Veiculo> buscaUltimaSemana(Date fim);

}
