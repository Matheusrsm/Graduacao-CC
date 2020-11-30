package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	CategoriaServiceImpl() {
		
	}

	@Override
	public List<Categoria> findAllCategorias() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public void saveCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
		
	}

	@Override
	public Optional<Categoria> findById(long id) {
		return this.categoriaRepository.findById(id);
	}

	@Override
	public Categoria updateCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
		return categoria;
	}

	@Override
	public void deleteCategoriaById(long id) {
		this.categoriaRepository.deleteById(id);
		
	}

	@Override
	public int size() {
		return this.categoriaRepository.findAll().size();
	}
	
	@Override
	public void deleteAllCategorias() {
		this.categoriaRepository.deleteAll();
	}

	@Override
	public Optional<Categoria> findByNome(String nome) {
		return this.categoriaRepository.findByNome(nome);
	}

}
