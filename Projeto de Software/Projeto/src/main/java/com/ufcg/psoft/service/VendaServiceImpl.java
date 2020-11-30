package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.Item;
import com.ufcg.psoft.model.Venda;
import com.ufcg.psoft.repository.ItemRepository;
import com.ufcg.psoft.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Optional<Venda> findById(long id) {
        return this.vendaRepository.findById(id);
    }

    @Override
    public void saveVenda(Venda venda) {
        this.vendaRepository.save(venda);
    }

    @Override
    public List<Venda> findAllVendas() {
        return this.vendaRepository.findAll();
    }

    @Override
    public void deleteVendaById(long id) {
        this.vendaRepository.deleteById(id);
    }

    @Override
    public void saveItem(Item item) {
        this.itemRepository.save(item);
    }


}
