package com.tinnova.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;
import com.tinnova.tests.test5.domain.service.VeiculoService;

@SpringBootTest
class CadastroVeiculoIntegrationTests {
	
	@Autowired
	private VeiculoService veiculoService;


	@Test
	public void whenCadastroVeiculoComDadosCorretos_ThenDeveAtribuirId() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("Onix");
		veiculo.setAno(2018);
		veiculo.setMarca(MarcasEnum.Chevrolet);
		veiculo.setVendido(false);
		veiculo.setDescricao("Branco, LTZ, completo");
		
		veiculo = veiculoService.salvar(veiculo);
		
		assertThat(veiculo).isNotNull();
		assertThat(veiculo.getId()).isNotNull();	
	}
	
}
