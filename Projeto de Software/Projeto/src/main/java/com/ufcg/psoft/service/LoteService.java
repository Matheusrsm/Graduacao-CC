package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import org.springframework.stereotype.Service;

@Service
public interface LoteService {

	List<Lote> findAllLotes();

	Lote findById(long id);

	void updateLote(Lote lote);

	void deleteLoteById(long id);

	int size();

	Lote saveLote(Lote lote);

	void setNumeroDeItens(Lote lote, int numeroDeItens);

	Optional<Lote> findByProduto(Produto produto);
}
