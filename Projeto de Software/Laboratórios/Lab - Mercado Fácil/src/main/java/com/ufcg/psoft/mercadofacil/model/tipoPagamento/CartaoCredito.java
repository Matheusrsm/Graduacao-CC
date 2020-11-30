package com.ufcg.psoft.mercadofacil.model.tipoPagamento;

public class CartaoCredito extends TipoPagamento {
	
	public CartaoCredito() {
		this.fatorDeAcrescimo = 1.05;
		this.nome = "Cartão de Crédito";
	}

}
