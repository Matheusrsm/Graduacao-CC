package com.ufcg.psoft.model;

import javax.persistence.*;

@Entity
@Table(name = Item.TB_ITEM)
public class Item {

    public static final String TB_ITEM = "TB_ITEM";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Produto produto;

    private int quantidade;

    public Item() {
    }

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public long getId() {
        return this.id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setProdutoNull() {
        this.produto = null;
    }
}
