package com.tinnova.tests.test5.util.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.tinnova.tests.test5.domain.model.Veiculo;
import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;
import com.tinnova.tests.test5.domain.service.VeiculoService;

@Component
public class DataLoader implements ApplicationRunner{
	
	private VeiculoService veiculoService;
	
	@Autowired
	public DataLoader(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		veiculoService.salvar(new Veiculo(1l, "Fiesta", MarcasEnum.Ford, 2020, "Branco, completo", false));
		veiculoService.salvar(new Veiculo(2l, "Gol", MarcasEnum.Volkswagen, 1998, "Branco, 1.0", false));
		veiculoService.salvar(new Veiculo(3l, "Celta", MarcasEnum.Chevrolet, 2008, "Cinza, 1.0, básico", false));
		veiculoService.salvar(new Veiculo(4l, "Focus", MarcasEnum.Ford, 2012, "Prata, 2.0, automático, completo", false));
		veiculoService.salvar(new Veiculo(5l, "Fox", MarcasEnum.Volkswagen, 2010, "Amarelo, 1.6, completo", true));
		veiculoService.salvar(new Veiculo(6l, "Ka", MarcasEnum.Ford, 2015, "Prata, completo", true));
		veiculoService.salvar(new Veiculo(7l, "Onix", MarcasEnum.Chevrolet, 2015, "Branco, LTZ, completo", false));
	}

}
