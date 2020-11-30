package com.ufcg.psoft.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.model.DTO.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.service.CategoriaService;
import com.ufcg.psoft.util.CustomErrorType;

@Controller
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	public Categoria criarCategoria(String categoria) {
		Optional<Categoria> requestCategoria = this.categoriaService.findByNome(categoria);
		if (!requestCategoria.isPresent()) {
			Categoria categoriaNova = new Categoria(categoria);
			this.categoriaService.saveCategoria(categoriaNova);
			return categoriaNova;
		}
		else return null;
	}

	public List<Categoria> listarCategorias() {
		return this.categoriaService.findAllCategorias();
	}

	public ResponseEntity<?> adicionarDesconto(String nome, int desconto) {
		if (desconto == 0 || desconto == 10 || desconto == 25 || desconto == 50) {
			if (categoriaExiste(nome)) {
				Categoria categoria = this.retornaCategoria(nome);
				categoria.setDesconto(new BigDecimal(((double)desconto)/100));
				this.categoriaService.saveCategoria(categoria);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(new CustomErrorType("Categoria " + nome + " não encontrada"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new CustomErrorType("Desconto de " + desconto + "% não é permitido"), HttpStatus.UNAUTHORIZED);
	}

	public boolean categoriaExiste(String categoria) {
		Optional<Categoria> possivelCategoria = this.categoriaService.findByNome(categoria);
		return possivelCategoria.isPresent();
	}

	public Categoria retornaCategoria(String categoria) {
		Optional<Categoria> possivelCategoria = this.categoriaService.findByNome(categoria);
		return possivelCategoria.get();
	}

}