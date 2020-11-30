package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repositories.CompraRepository;
import com.ufcg.psoft.mercadofacil.repositories.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.repositories.UsuarioRepository;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {

		Optional<Usuario> optUsuario = usuarioRepository.findById(usuario.getCpf());

		if (optUsuario.isPresent()) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("O usuário " + usuario.getNome()
					+ " ja esta cadastrado!"), HttpStatus.CONFLICT);
		}

		usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUsuario(@PathVariable("id") String id, @RequestBody Usuario usuario) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (!optionalUsuario.isPresent()) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuario with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		Usuario currentUsuario = optionalUsuario.get();

		currentUsuario.setNome(usuario.getNome());
		currentUsuario.setCarrinho(usuario.getCarrinho());
		currentUsuario.setCompras(usuario.getCompras());

		usuarioRepository.save(currentUsuario);

		return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
	}
	
	// UsuarioNormal = 1; UsuarioEspecial = 2; UsuarioPremium = 3;
	@RequestMapping(value = "/usuario/{id}/mudaPerfil/{tipo}", method = RequestMethod.PUT)
	public ResponseEntity<?> mudarPerfilUsuario(@PathVariable("id") String id, @PathVariable("tipo") int tipo) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (!optionalUsuario.isPresent()) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuario with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		Usuario currentUsuario = optionalUsuario.get();
		currentUsuario.mudaPerfilUsuario(tipo);

		usuarioRepository.save(currentUsuario);

		return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") String id) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (!optionalUsuario.isPresent()) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuario with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		usuarioRepository.delete(optionalUsuario.get());

		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}/adicionarProduto/{idProduto}", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarProdutoAoCarrinho(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idProduto") long idProduto) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

		if (!optionalUsuario.isPresent()) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Usuario with id " + idUsuario + " not found"), HttpStatus.NOT_FOUND);
		}
		
		Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);

		if (!optionalProduto.isPresent())
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Produto with id " + idProduto + " not found"), HttpStatus.NOT_FOUND);

		optionalUsuario.get().adicionarProdutoAoCarrinho(optionalProduto.get());
		
		usuarioRepository.save(optionalUsuario.get());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{idUsuario}/removerProduto/{idProduto}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerProdutoDoCarrinho(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idProduto") long idProduto) {

		Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);

		if (!optionalProduto.isPresent())
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Produto with id " + idProduto + " not found"), HttpStatus.NOT_FOUND);

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

		if (!optionalUsuario.isPresent())
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Usuario with id " + idUsuario + " not found"), HttpStatus.NOT_FOUND);

		optionalUsuario.get().removerProdutoDoCarrinho(optionalProduto.get());
		usuarioRepository.save(optionalUsuario.get());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Boleto = 1; Paypal = 2; CartaoCredito = 3;
	@RequestMapping(value = "/usuario/{idUsuario}/compra/{tipoPagamento}", method = RequestMethod.PUT)
	public ResponseEntity<?> finalizarCompra(@PathVariable("idUsuario") String idUsuario, @PathVariable("tipoPagamento") int tipoPagamento) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

		if (!optionalUsuario.isPresent())
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Usuario with id " + idUsuario + " not found"), HttpStatus.NOT_FOUND);

		Compra compra = optionalUsuario.get().efetuarCompra(tipoPagamento);
		
		usuarioRepository.save(optionalUsuario.get());
		compraRepository.save(compra);
		
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}/compras", method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras(@PathVariable("idUsuario") String idUsuario) {
		List<Compra> compras = usuarioRepository.findById(idUsuario).get().getCompras();

		if (compras.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}/compra/{idCompra}", method = RequestMethod.GET)
	public ResponseEntity<?> detalharCompra(@PathVariable("idUsuario") String idUsuario, @PathVariable("idCompra") long idCompra) {
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
		
		if (!optionalUsuario.isPresent())
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Usuario with id " + idUsuario + " not found"), HttpStatus.NOT_FOUND);
		
		Optional<Compra> optionalCompra = compraRepository.findById(idCompra);
		
		if (!optionalCompra.isPresent()) 
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Compra with id " + idCompra + " not found"), HttpStatus.NOT_FOUND);
		
		Compra compra = optionalCompra.get();
		
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);
	}
}
