package com.ufcg.psoft.model.DTO;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.Produto;
import exceptions.ObjetoInvalidoException;

import java.math.BigDecimal;

public class ProdutoDTODescricaoUsuario {

    private String nome;

    private BigDecimal preco;

    private String fabricante;

    private String categoria;

    private String situacao;

    public ProdutoDTODescricaoUsuario(String nome, BigDecimal preco, String fabricante, String categoria, int situacao) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.categoria = categoria;
        if (situacao == 1) {
            this.situacao = "Disponível";
            this.preco = preco;

        } else {
            this.situacao = "Indisponível";
        }

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getSituacao() {
        return situacao;
    }

}
