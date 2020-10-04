package com.tinnova.tests.test5.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tinnova.tests.test5.domain.exception.EntidadeNaoEncontradaException;
import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	public Veiculo salvar(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	public void excluir(Long veiculoId) {
		try {
			veiculoRepository.deleteById(veiculoId);
		} catch (EmptyResultDataAccessException  e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de veículo com código %d", veiculoId));		
			}
		
	}

}
