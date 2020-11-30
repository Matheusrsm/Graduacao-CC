package com.ufcg.psoft.model.DTO;


public class ProdutoDTO {

    private String nome;
    private String codigoBarra;
    private String fabricante;
    private String categoria;

    public ProdutoDTO(){
    }

    public ProdutoDTO(String nome, String codigoBarra, String fabricante, String categoria){
        this.nome = nome;
        this.codigoBarra = codigoBarra;
        this.fabricante = fabricante;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
