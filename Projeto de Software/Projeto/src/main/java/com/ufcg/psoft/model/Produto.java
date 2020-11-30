package com.ufcg.psoft.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = Produto.TB_PRODUTO)
public class Produto {

	public static final String TB_PRODUTO = "TB_PRODUTO";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	private BigDecimal preco;

	private String codigoBarra;

	private String fabricante;

	private boolean produtoVencido;

	@ManyToOne(cascade=CascadeType.MERGE)
	private Categoria categoria;

	public int situacao; // usa variaveis estaticas abaixo
	/* situacoes do produto */
	public static final int DISPONIVEL = 1;
	public static final int INDISPONIVEL = 2;

	public Produto() {
		this.preco = new BigDecimal(0);
	}

	public Produto(String nome, String codigoBarra, String fabricante,
				   Categoria categoria) {
		this.nome = nome;
		this.preco = new BigDecimal(0);
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = categoria;
		this.situacao = Produto.INDISPONIVEL;
	}

	public Produto(String nome, String codigoBarra, String fabricante,
				   Categoria categoria, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = categoria;
		this.situacao = Produto.INDISPONIVEL;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public long getId() {
		return id;
	}

	public void setId(long codigo) {
		this.id = codigo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void tornaDisponivel() {
		this.situacao = DISPONIVEL;
	}

	public void tornaIndisponivel() {
		this.situacao = INDISPONIVEL;
	}

	public int getSituacao() {
		return this.situacao;
	}

	public boolean isIndisponivel() {
		return situacao == INDISPONIVEL;
	}

	public boolean isProdutoVencido() {
		return produtoVencido;
	}

	public void setProdutoVencido(boolean vencido) {
		this.produtoVencido = vencido;
		if (produtoVencido) {
			this.situacao = Produto.INDISPONIVEL;
		}
	}

	@Override
	public String toString(){
		return "Produto{" +
				"id=" + id +
				", nome=" + nome +
				", preco=" + preco +
				", codigoBarra=" + codigoBarra +
				", fabricante='" + fabricante + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
