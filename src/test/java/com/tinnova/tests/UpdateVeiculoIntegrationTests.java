package com.tinnova.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;
import com.tinnova.tests.test5.domain.repository.VeiculoRepository;
import com.tinnova.tests.test5.domain.service.VeiculoService;

@SpringBootTest
public class UpdateVeiculoIntegrationTests {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private VeiculoService veiculoService;

	@Test
	public void whenVeiculoCadastradoComSucesso_ThenDeveBuscarVeiculoComValoresCorretos() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("New Fiesta");
		veiculo.setAno(2015);
		veiculo.setMarca(MarcasEnum.Ford);
		veiculo.setVendido(false);
		veiculo.setDescricao("Branco, completo");
		
		veiculo = veiculoService.salvar(veiculo);
		
		Optional<Veiculo> veiculoSalvo = veiculoRepository.findById(veiculo.getId());
		
		assertThat(veiculoSalvo.isPresent()).isTrue();
		assertThat(veiculoSalvo.get().getAno()).isEqualTo(veiculo.getAno());
		assertThat(veiculoSalvo.get().getId()).isEqualTo(veiculo.getId());
		assertThat(veiculoSalvo.get().getDescricao()).isEqualTo(veiculo.getDescricao());
		assertThat(veiculoSalvo.get().getVeiculo()).isEqualTo(veiculo.getVeiculo());
		assertThat(veiculoSalvo.get().getMarca()).isEqualTo(veiculo.getMarca());
	}
	

}
