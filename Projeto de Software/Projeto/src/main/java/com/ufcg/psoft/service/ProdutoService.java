package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.DTO.ProdutoDTO;
import com.ufcg.psoft.model.Produto;
import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {

	List<Produto> findAllProdutos();

	Produto saveProduto(Produto produto);

	Optional<Produto> findByCodigoDeBarras(String codigoBarra);

	Optional<Produto> findById(long id);

	Produto updateProduto(Produto produto);

	void deleteProdutoById(long id);

	int size();

	BigDecimal getPreco(Produto produto);

	BigDecimal getDesconto(Produto produto);
}
