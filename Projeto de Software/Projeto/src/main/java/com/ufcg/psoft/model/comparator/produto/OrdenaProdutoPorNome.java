package com.ufcg.psoft.model.comparator.produto;

import com.ufcg.psoft.model.Produto;

import java.util.Comparator;

public class OrdenaProdutoPorNome implements Comparator<Produto> {

    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto1.getNome().compareTo(produto2.getNome());
    }
}
