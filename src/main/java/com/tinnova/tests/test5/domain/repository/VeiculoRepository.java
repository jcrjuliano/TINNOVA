package com.tinnova.tests.test5.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnova.tests.test5.domain.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQuery{

}
