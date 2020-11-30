package com.ufcg.psoft.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Categoria.TB_CATEGORIA)
public class Categoria {
	
	public static final String TB_CATEGORIA = "TB_CATEGORIA";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private BigDecimal desconto;
	
	public Categoria() {
		this.desconto = new BigDecimal(0);
	}
	
	public Categoria(String nome) {
		this.nome = nome;
		this.desconto = new BigDecimal(0);
	}

	public long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
}
