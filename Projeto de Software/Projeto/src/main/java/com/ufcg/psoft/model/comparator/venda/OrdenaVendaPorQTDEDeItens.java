package com.ufcg.psoft.model.comparator.venda;

import com.ufcg.psoft.model.Venda;

import java.util.Comparator;

public class OrdenaVendaPorQTDEDeItens implements Comparator<Venda> {
    @Override
    public int compare(Venda venda1, Venda venda2) {
        return venda1.getQtdTotalItens() - venda2.getQtdTotalItens();
    }
}
