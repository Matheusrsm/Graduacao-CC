package com.ufcg.psoft.model;

import java.math.BigDecimal;
import java.util.List;

public class Relatorio {

    private int qntdTotalProdutos;
    private int qntdTotalLotes;
    private List<Lote> lotes;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private BigDecimal receitaTotal;

    public Relatorio(){}

    public Relatorio(List<Lote> lotes, List<Produto> produtos, List<Venda> vendas){
        this.qntdTotalProdutos = produtos.size();
        this.qntdTotalLotes = lotes.size();
        this.lotes = lotes;
        this.produtos = produtos;
        this.vendas = vendas;
        calculaReceitaTotal();
    }

    private void calculaReceitaTotal() {
        this.receitaTotal = new BigDecimal(0);
        for(Venda venda : vendas){
            this.receitaTotal = this.receitaTotal.add(venda.getPreco());
        }
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public int getQntdTotalProdutos() {
        return qntdTotalProdutos;
    }

    public void setQntdTotalProdutos(int qntdTotalProdutos) {
        this.qntdTotalProdutos = qntdTotalProdutos;
    }

    public int getQntdTotalLotes() {
        return qntdTotalLotes;
    }

    public void setQntdTotalLotes(int qntdTotalLotes) {
        this.qntdTotalLotes = qntdTotalLotes;
    }

    public BigDecimal getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(BigDecimal receitaTotal) {
        this.receitaTotal = receitaTotal;
    }
}
