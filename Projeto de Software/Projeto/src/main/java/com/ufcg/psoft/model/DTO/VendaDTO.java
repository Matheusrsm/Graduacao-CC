package com.ufcg.psoft.model.DTO;

import java.util.HashMap;
import java.util.Map;

public class VendaDTO {

    private Map<Long, Integer> produtos;

    public VendaDTO(){
        this.produtos = new HashMap<>();
    }

    public Map<Long, Integer>getProdutos(){
        return this.produtos;
    }

    public void setProdutos(Map<Long, Integer> produtos){
        this.produtos = produtos;
    }

}
