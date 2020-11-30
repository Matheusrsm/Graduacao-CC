package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.Item;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.Venda;

import org.springframework.stereotype.Service;

@Service
public interface VendaService {
    
    Optional<Venda> findById(long id);

    void saveVenda(Venda venda);

    List<Venda> findAllVendas();

    void deleteVendaById(long id);

    void saveItem(Item item);
}
