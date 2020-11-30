package com.ufcg.psoft.controller;

import java.util.*;

import com.ufcg.psoft.model.Item;
import com.ufcg.psoft.model.Venda;
import com.ufcg.psoft.model.comparator.venda.OrdenaVendaPorPrecoTotal;
import com.ufcg.psoft.model.comparator.venda.OrdenaVendaPorQTDEDeItens;
import com.ufcg.psoft.repository.ItemRepository;
import com.ufcg.psoft.service.VendaService;
import com.ufcg.psoft.util.CustomErrorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import exceptions.ObjetoInvalidoException;

@Controller
public class VendaController {
    
    @Autowired
    VendaService vendaService;

    public ResponseEntity<?> registrarVenda(Venda venda) {
        this.vendaService.saveVenda(venda);
        return new ResponseEntity<Venda>(venda, HttpStatus.CREATED);
    }

    public int qtdItensVenda(Collection<Integer> values) {
        int qtd = 0;

        for (Integer i : values) {
            qtd += i;
        }

        return qtd;
    }

    public void validaVenda(Map<Long, Integer> produtos, double preco, int qtdTotal) throws ObjetoInvalidoException {
        if (produtos.isEmpty() || produtos == null) {
            throw new ObjetoInvalidoException("Lista de produtos não pode ser vazia ou nula.");
        }
        if (preco < 0) {
            throw new ObjetoInvalidoException("Preço não pode ser negativo.");
        }
        if (qtdTotal <= 0) {
            throw new ObjetoInvalidoException("Deve haver uma quantidade de itens.");
        }
    }

    public ResponseEntity<?> listarVendasOrdenadas(String ordenacao) {
        List<Venda> vendas = vendaService.findAllVendas();
        Comparator comparator;

        switch (ordenacao.toLowerCase()) {
            case "preco": comparator = new OrdenaVendaPorPrecoTotal(); break;
            case "quantidade": comparator = new OrdenaVendaPorQTDEDeItens(); break;
            default: return new ResponseEntity<CustomErrorType>(new CustomErrorType("Tipo de ordenação: " + ordenacao + " é inválida"), HttpStatus.BAD_REQUEST);
        }
        Collections.sort(vendas, comparator);
        return vendas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<Venda>>(vendas, HttpStatus.OK);
    }

    public List<Venda> getVendas(){
        return this.vendaService.findAllVendas();
    }

    public void deleteVenda(long id) {
        Venda venda = this.retornaVenda(id).get();
        venda.removeItens();
        this.vendaService.saveVenda(venda);
        this.vendaService.deleteVendaById(id);
    }

    public Optional<Venda> retornaVenda(long id) {
        return this.vendaService.findById(id);
    }

    public void salvarItem(Item item) {
        this.vendaService.saveItem(item);
    }
}
