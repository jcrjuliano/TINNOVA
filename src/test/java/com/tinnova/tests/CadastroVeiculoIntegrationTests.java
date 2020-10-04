package com.tinnova.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;
import com.tinnova.tests.test5.domain.repository.VeiculoRepository;
import com.tinnova.tests.test5.domain.service.VeiculoService;

@SpringBootTest
class CadastroVeiculoIntegrationTests {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
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
	
	

	private void limpaBancoECadastraTresVeiculos() {
		
		veiculoRepository.deleteAll();
		
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo("New Fiesta");
		veiculo.setAno(2015);
		veiculo.setMarca(MarcasEnum.Ford);
		veiculo.setVendido(true);
		veiculo.setDescricao("Branco, completo");
		
		veiculoService.salvar(veiculo);
		
		Veiculo veiculo2 = new Veiculo();
		veiculo2.setVeiculo("Gol");
		veiculo2.setAno(2016);
		veiculo2.setMarca(MarcasEnum.Volkswagen);
		veiculo2.setVendido(false);
		veiculo2.setDescricao("Power 1.6, Preto, Completo");
		
		veiculoService.salvar(veiculo2);
		
		Veiculo veiculo3 = new Veiculo();
		veiculo3.setVeiculo("Uno");
		veiculo3.setAno(2005);
		veiculo3.setMarca(MarcasEnum.Fiat);
		veiculo3.setVendido(false);
		veiculo3.setDescricao("Cinza, 1.0, básico.");
		
		veiculoService.salvar(veiculo3);
	}
	
	

}
