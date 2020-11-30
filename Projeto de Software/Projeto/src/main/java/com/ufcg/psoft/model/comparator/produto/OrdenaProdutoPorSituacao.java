package com.ufcg.psoft.model.comparator.produto;

import com.ufcg.psoft.model.Produto;
import exceptions.ObjetoInvalidoException;

import java.util.Comparator;

public class OrdenaProdutoPorSituacao implements Comparator<Produto> {

    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto2.getSituacao() - produto1.getSituacao();
    }
}
