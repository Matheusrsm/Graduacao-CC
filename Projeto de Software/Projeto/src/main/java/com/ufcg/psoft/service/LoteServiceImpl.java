package com.ufcg.psoft.service;


import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;

@Service
public class LoteServiceImpl implements LoteService {

	@Autowired
	LoteRepository loteRepository;

	@Override
	public Lote saveLote(Lote lote) {
		this.loteRepository.save(lote);
		return lote;
	}

	@Override
	public Lote findById(long id) {
		return this.loteRepository.findById(id).get();
	}

	@Override
	public void updateLote(Lote lote) {
		this.loteRepository.save(lote);
	}

	@Override
	public void deleteLoteById(long id) {
		this.loteRepository.deleteById(id);
	}

	@Override
	public List<Lote> findAllLotes() {
		return this.loteRepository.findAll();
	}

	@Override
	public int size() {
		return this.loteRepository.findAll().size();
	}

	@Override
	public void setNumeroDeItens(Lote lote, int numeroDeItens) {
		lote.setNumeroDeItens(numeroDeItens);
		this.loteRepository.save(lote);
	}

	@Override
	public Optional<Lote> findByProduto(Produto produto) {
		return this.loteRepository.findByProduto(produto);
	}

}
