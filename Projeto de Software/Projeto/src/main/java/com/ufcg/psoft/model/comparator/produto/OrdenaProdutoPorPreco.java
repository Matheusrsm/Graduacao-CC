package com.ufcg.psoft.model.comparator.produto;

import com.ufcg.psoft.model.Produto;

import java.util.Comparator;

public class OrdenaProdutoPorPreco implements Comparator<Produto> {
    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto1.getPreco().compareTo(produto2.getPreco());
    }
}
