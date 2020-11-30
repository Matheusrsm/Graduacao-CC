package com.ufcg.psoft.mercadofacil.model.tipoPagamento;

public class Paypal extends TipoPagamento{

	public Paypal() {
		this.fatorDeAcrescimo = 1.02;
		this.nome = "Paypal";
	}
}
