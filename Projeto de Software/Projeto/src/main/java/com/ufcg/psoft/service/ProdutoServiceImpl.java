package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	ProdutoServiceImpl() {
	}

	public List<Produto> findAllProdutos() {
		return this.produtoRepository.findAll();
	}

	public Produto saveProduto(Produto produto) {
		this.produtoRepository.save(produto);
		return produto;
	}

	@Override
	public Optional<Produto> findByCodigoDeBarras(String codigoBarra) {
		return this.produtoRepository.findByCodigoBarra(codigoBarra);
	}

	public Produto updateProduto(Produto produto) {
		this.produtoRepository.save(produto);
		return produto;
	}

	public void deleteProdutoById(long id) {
		this.produtoRepository.deleteById(id);
	}

	public int size() {
		return this.produtoRepository.findAll().size();
	}

	public void deleteAllProdutos() {
		this.produtoRepository.deleteAll();
	}

	public Optional<Produto> findById(long id) {
		return this.produtoRepository.findById(id);
	}

	@Override
	public BigDecimal getPreco(Produto produto) {
		return produto.getPreco();
	}

	@Override
	public BigDecimal getDesconto(Produto produto) {
		return produto.getCategoria().getDesconto();
	}

}
