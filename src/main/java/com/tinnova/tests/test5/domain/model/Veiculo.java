package com.tinnova.tests.test5.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinnova.tests.test5.domain.model.enums.MarcasEnum;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String veiculo;
	
	@Column
	@Enumerated(EnumType.STRING)
	private MarcasEnum marca;
	
	@Column
	private Integer ano;
	
	@Column
	private String descricao;
	
	@Column
	private Boolean vendido;
	
	@JsonIgnore
	private Integer decada;
	
	@Column
	@CreatedDate
	private Date created;
	
	@Column
	@LastModifiedDate
	private Date updated;

	public Veiculo(Long id, String veiculo, MarcasEnum marca, Integer ano, String descricao, Boolean vendido) {
		this.id = id;
		this.veiculo = veiculo;
		this.marca = marca;
		this.ano = ano;
		this.descricao = descricao;
		this.vendido = vendido;
		this.created = new Date();
		this.updated = new Date();
	}
	
	@PrePersist
    protected void prePersist() {
        if (this.created == null) created = new Date();
        if (this.updated == null) updated = new Date();
        if (this.decada == null) updateDecada();
        
    }
	
	@PreUpdate
	 protected void preUpdate() {
        this.updated = new Date();
    }
	 
	public Veiculo() {
		
	}
	
	public void updateDecada() {
		setDecada(Integer.valueOf(ano/10 + "0"));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public MarcasEnum getMarca() {
		return marca;
	}

	public void setMarca(MarcasEnum marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
		updateDecada();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getDecada() {
		return decada;
	}

	public void setDecada(Integer decada) {
		this.decada = decada;
	}

	
	
	
}
