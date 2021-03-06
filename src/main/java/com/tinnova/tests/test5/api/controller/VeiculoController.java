package com.tinnova.tests.test5.api.controller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.tests.test5.domain.exception.EntidadeNaoEncontradaException;
import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorDecada;
import com.tinnova.tests.test5.domain.model.dto.DistribuicaoPorFabricante;
import com.tinnova.tests.test5.domain.repository.VeiculoRepository;
import com.tinnova.tests.test5.domain.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private VeiculoService cadastroVeiculo;
	
	
	/*
	 * Todos veículos
	 * Parametro Opcional: Apenas Disponiveis, para listar apenas os veículos com o flag de vendido = false.
	 * Parametro de URL: apenasDisponiveis
	 */		
	@GetMapping
	public List<Veiculo> listAll(@RequestParam(required = false) Boolean apenasDisponiveis){
		if (apenasDisponiveis == null) {
			apenasDisponiveis = false;
		}
		return apenasDisponiveis ? veiculoRepository.findByVendidoFalse() : veiculoRepository.findAll();
	}
	
	@GetMapping("/{veiculoId}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId){
		Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
		
		if (veiculo.isPresent()) {
			return ResponseEntity.ok(veiculo.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/*
	 * Busca a quantidade de veículos por década
	 * Parametro Opcional: Apenas Disponiveis, para listar apenas os veículos com o flag de vendido = false.
	 * Parametro de URL: apenasDisponiveis
	 */
	@GetMapping("/por-decada")
	public List<DistribuicaoPorDecada> buscarPorDecada(@RequestParam(required = false) Boolean apenasDisponiveis){
		if (apenasDisponiveis == null) {
			apenasDisponiveis = false;
		}
		
		return apenasDisponiveis ? veiculoRepository.buscaDisponiveisPorDecada() : veiculoRepository.buscaPorDecada();

	}
	
	/*
	 * Busca a quantidade de veículos por Fabricante
	 * Parametro Opcional: Apenas Disponiveis, para listar apenas os veículos com o flag de vendido = false.
	 * Parametro de URL: apenasDisponiveis
	 */
	@GetMapping("/por-fabricante")
	public List<DistribuicaoPorFabricante> buscarPorFabricante(@RequestParam(required = false) Boolean apenasDisponiveis){
		if (apenasDisponiveis == null) {
			apenasDisponiveis = false;
		}
		return apenasDisponiveis? veiculoRepository.buscaDisponiveisPorFabricante() : veiculoRepository.buscaPorFabricante();
	}
	
	@GetMapping("/ultima-semana")
	public List<Veiculo> buscaRegistradosNaUltimaSemana(){
	    Date fim = new Date(new Date().getTime() - 7 * 24 * 3600 * 1000l); 
		
		return veiculoRepository.buscaUltimaSemana(fim);
	}
	
	/*
	 * Retorna a quantidade de veículos disponíveis.
	 */	
	@GetMapping("/disponiveis")
	public Integer exibirQuantidadeDeNaoVendidos() {
		return veiculoRepository.findByVendidoFalse().size();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Veiculo veiculo){
		try {
			veiculo = cadastroVeiculo.salvar(veiculo);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{veiculoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long veiculoId, @RequestBody Veiculo veiculo){
		try {
			Veiculo veiculoAtual = veiculoRepository.findById(veiculoId).orElse(null);
			
			if(veiculoAtual != null) {
				BeanUtils.copyProperties(veiculo,  veiculoAtual, "id");
				
				veiculoAtual = cadastroVeiculo.salvar(veiculoAtual);
				
				return ResponseEntity.ok(veiculoAtual);
			}
			
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{veiculoId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long veiculoId, @RequestBody Map<String, Object> campos){
		
		Veiculo veiculoAtual = veiculoRepository.findById(veiculoId).orElse(null);
		
		if(veiculoAtual == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, veiculoAtual);
		
		return atualizar(veiculoId, veiculoAtual);
		
	}
	
	/*
	 * Método usado para identificar e atualizar os campos apenas os campos solicitados na atualização parcial com Patch
	 * 
	 */
	private void merge(Map<String, Object> dadosOrigem, Veiculo veiculoDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Veiculo veiculoOrigem = objectMapper.convertValue(dadosOrigem, Veiculo.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			
			Field field = ReflectionUtils.findField(Veiculo.class, nomePropriedade);
			
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, veiculoOrigem);
						
			ReflectionUtils.setField(field, veiculoDestino, novoValor);
		});
	}
	
	@DeleteMapping("/{veiculoId}")
	public ResponseEntity<?> remover(@PathVariable Long veiculoId){
		try {
			cadastroVeiculo.excluir(veiculoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException  e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
