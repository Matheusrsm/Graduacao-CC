package com.ufcg.psoft.mercadofacil.model.perfilUsuario;

public abstract class PerfilUsuario {
	
	protected String nome;
	protected double fatorDeDesconto;
	
	public double getDesconto() {
		return fatorDeDesconto;
	}

}
