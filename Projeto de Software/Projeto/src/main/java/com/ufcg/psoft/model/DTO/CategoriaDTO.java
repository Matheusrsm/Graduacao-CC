package com.ufcg.psoft.model.DTO;

public class CategoriaDTO {

    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
