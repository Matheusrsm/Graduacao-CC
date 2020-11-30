package com.ufcg.psoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Categoria;

@Service
public interface CategoriaService {

	List<Categoria> findAllCategorias();

	void saveCategoria(Categoria categoria);

	Optional<Categoria> findById(long id);
	
	Optional<Categoria> findByNome(String nome);

	Categoria updateCategoria(Categoria categoria);

	void deleteCategoriaById(long id);
	
	void deleteAllCategorias();

	int size();
}
