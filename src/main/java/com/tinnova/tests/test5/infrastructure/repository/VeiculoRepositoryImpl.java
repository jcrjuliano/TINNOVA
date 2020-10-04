package com.tinnova.tests.test5.infrastructure.repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorFabricante;
import com.tinnova.tests.test5.domain.repository.VeiculoRepositoryQuery;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	/*
	 * query para buscar com filtros dinamicos
	 */
	@Override
	public List<Veiculo> find(String veiculo, String marca, Integer ano, String descricao, Boolean vendido) {
var builder = manager.getCriteriaBuilder();
		
		var criteria = builder.createQuery(Veiculo.class);
		var root = criteria.from(Veiculo.class);

		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasText(veiculo)) {
			predicates.add(builder.like(root.get("veiculo"), "%" + veiculo + "%"));
		}
		
		if (StringUtils.hasText(marca)) {
			predicates.add(builder.like(root.get("marca"), "%" + marca + "%"));
		}
		
		if (ano != null) {
			predicates.add(builder.equal(root.get("ano"), ano));
		}
		
		if (StringUtils.hasText(descricao)) {
			predicates.add(builder.like(root.get("descricao"), "%" + descricao + "%"));
		}
		
		if (vendido != null) {
			predicates.add(builder.equal(root.get("vendido"), vendido));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
	}
	


	@Override
	public List<DistribuicaoPorFabricante> buscaPorFabricante() {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DistribuicaoPorFabricante.class);
		var root = query.from(Veiculo.class);
		
		var selection = builder.construct(DistribuicaoPorFabricante.class, root.get("marca"), builder.count(root.get("id")));
		
		query.select(selection);
		query.groupBy(root.get("marca"));
		return manager.createQuery(query).getResultList();
		
	}
	
}