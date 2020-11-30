package com.ufcg.psoft.model.comparator.venda;

import com.ufcg.psoft.model.Venda;

import java.util.Comparator;

public class OrdenaVendaPorPrecoTotal implements Comparator<Venda> {
    @Override
    public int compare(Venda venda1, Venda venda2) {
        return venda1.getPreco().compareTo(venda2.getPreco());
    }
}
