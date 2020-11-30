package com.ufcg.psoft.mercadofacil.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CarrinhoDeCompras {
	
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Produto> produtos;
	
	private int disponibilidade;
	
	public static final int OCUPADO = 1, VAZIO = 2;
	
	public CarrinhoDeCompras() {
		this.produtos = new ArrayList<Produto>();
		this.disponibilidade = VAZIO;
	}
	
	public void adicionarAoCarrinho(Produto produto) {
		if (produtos.isEmpty())
			disponibilidade = OCUPADO;
		if (produto.situacao == Produto.DISPONIVEL)
			produtos.add(produto);
	}
	
	public void removerDoCarrinho(Produto produto) {
		if (produtos.contains(produto))
			produtos.remove(produto);
	}
	
	public void limparCarrinho() {
		produtos.clear();
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
}