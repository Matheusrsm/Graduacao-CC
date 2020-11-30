package com.ufcg.psoft.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = Venda.TB_VENDA)
public class Venda {
    
    public static final String TB_VENDA = "TB_VENDA";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<Item> itens;

    private BigDecimal precoTotal;

    private int qtdTotalItens;

    public Venda() {

    }

    public Venda(List<Item> itens, double preco, int qtdTotalItens) {
        this.itens = itens;
        this.qtdTotalItens = qtdTotalItens;
        this.precoTotal = new BigDecimal(preco).setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<Item> getItens() {
        return this.itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal getPreco() {
        return this.precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getQtdTotalItens() {
        return this.qtdTotalItens;
    }

    public void setQtdTotalItens(int qtdItens) {
        this.qtdTotalItens = qtdItens;
    }

    public long getId() {
        return this.id;
    }

    public void removeItens() {
        this.itens = null;
    }

    @Override
    public String toString(){
        return "Venda {" +
                "id=" + id +
                ", produtos=" + itens.toString() +
                ", qntdTotalItens=" + qtdTotalItens +
                ", precoTotal='" + precoTotal + '\'' +
                '}';
    }
}
