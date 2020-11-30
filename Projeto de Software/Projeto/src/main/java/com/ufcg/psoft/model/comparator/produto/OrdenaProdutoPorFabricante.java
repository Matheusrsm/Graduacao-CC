package com.ufcg.psoft.model.comparator.produto;

import com.ufcg.psoft.model.Produto;

import java.util.Comparator;

public class OrdenaProdutoPorFabricante implements Comparator<Produto> {
    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto1.getFabricante().compareTo(produto2.getFabricante());
    }
}
