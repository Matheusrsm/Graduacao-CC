package com.ufcg.psoft.mercadofacil.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ufcg.psoft.mercadofacil.model.perfilUsuario.PerfilUsuario;
import com.ufcg.psoft.mercadofacil.model.perfilUsuario.UsuarioEspecial;
import com.ufcg.psoft.mercadofacil.model.perfilUsuario.UsuarioNormal;
import com.ufcg.psoft.mercadofacil.model.perfilUsuario.UsuarioPremium;

@Entity
public class Usuario {

	@Id
	private String cpf;

	private String nome;
	
	private PerfilUsuario perfilUsuario;

	@OneToOne
	private CarrinhoDeCompras carrinho;

	@OneToMany
	private List<Compra> compras;
	
	public Usuario() {
		this.perfilUsuario = new UsuarioNormal();
		this.carrinho = new CarrinhoDeCompras();
		this.compras = new ArrayList<Compra>();
	}

	public Usuario(String cpf, String nome) {
		this();
		this.nome = nome;
		this.cpf = cpf;
	}

	public void adicionarProdutoAoCarrinho(Produto produto) {
		carrinho.adicionarAoCarrinho(produto);
	}

	public void removerProdutoDoCarrinho(Produto produto) {
		carrinho.removerDoCarrinho(produto);
	}

	public Compra efetuarCompra(int tipoDePagamento) {
		Compra compra = new Compra();
		if (perfilUsuario instanceof UsuarioEspecial && carrinho.getProdutos().size() > 10)
			compra.realizarCompra(carrinho, tipoDePagamento, perfilUsuario.getDesconto());
		else if (perfilUsuario instanceof UsuarioPremium && carrinho.getProdutos().size() > 5)
			compra.realizarCompra(carrinho, tipoDePagamento, perfilUsuario.getDesconto());
		else if (perfilUsuario instanceof UsuarioNormal)
			compra.realizarCompra(carrinho, tipoDePagamento, perfilUsuario.getDesconto());
		return compra;
		/*return "Detalhamento da compra:\n\n" + compra.realizarCompra(carrinho, tipoDePagamento)
				+ "\nObrigado por comprar no Mercado Fácil!";*/
	}

	public String getCpf() {
		return cpf;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}

	public String getNome() {
		return nome;
	}

	public List<Compra> getCompras() {
		return compras;
	}
	
	public Compra getCompra(long codigo) {
		for (Compra compra : compras) {
			if (compra.getCodigo() == codigo)
				return compra;
		}
		return null;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void mudaPerfilUsuario(int tipo) {
		switch(tipo) {
		case 2:
			setTipoUsuario(new UsuarioEspecial());
			break;
		case 3:
			setTipoUsuario(new UsuarioPremium());
			break;
		default:
			setTipoUsuario(new UsuarioNormal());
			break;
		}
	}
	
	public PerfilUsuario getTipoUsuario() {
		return perfilUsuario;
	}

	public void setTipoUsuario(PerfilUsuario tipoUsuario) {
		this.perfilUsuario = tipoUsuario;
	}
	
	/*public String listarCompras() {
		String detalhamentoDasCompras = "Compras realizadas pelo usuário:\n";
		for (Compra compra : compras)
			detalhamentoDasCompras += "\n\n" + compra.toStringSimples();
		return detalhamentoDasCompras;
	}*/
}