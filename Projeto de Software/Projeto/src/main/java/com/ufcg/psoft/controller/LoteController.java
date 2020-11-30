package com.ufcg.psoft.controller;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoteController {

	@Autowired
	LoteService loteService;

	public Lote criarLote(Lote lote) {
		return this.loteService.saveLote(lote);
	}

	public List<Lote> listarLotes() {
		return this.loteService.findAllLotes();
	}

	public Lote retornaLote(Produto produto) {
		return this.loteService.findByProduto(produto).get();
	}

	public Lote atualizaQtdDeProdutos(int qtd, Produto produto) {
		Lote lote = this.retornaLote(produto);
		lote.adicionaItens(qtd);
		return lote;
	}

	public void salvarLote(Lote lote) {
		this.loteService.saveLote(lote);
	}

	public List<Lote> listarLotesProximosDoVencimento() throws ParseException {
		List<Lote> lotesProximosDoVencimento = new ArrayList<Lote>();
		for (Lote lote : this.loteService.findAllLotes()) {
			if (lote.isLoteProximoDoVencimento()) {
				lotesProximosDoVencimento.add(lote);
			}
		}
		return lotesProximosDoVencimento;
	}

	public List<Lote> listarLotesBaixa() {
		List<Lote> lotesBaixa = new ArrayList<>();
		for (Lote lote : this.loteService.findAllLotes()) {
			if (lote.getNumeroDeItens() < 15) {
				lotesBaixa.add(lote);
			}
		}
		return lotesBaixa;
	}
}
