package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ufcg.psoft.mercadofacil.model.tipoPagamento.Boleto;
import com.ufcg.psoft.mercadofacil.model.tipoPagamento.CartaoCredito;
import com.ufcg.psoft.mercadofacil.model.tipoPagamento.Paypal;
import com.ufcg.psoft.mercadofacil.model.tipoPagamento.TipoPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue
	private Long codigo;

	@OneToMany
	private List<Produto> produtos;

	private String data;

	private BigDecimal valor;

	private TipoPagamento tipoPagamento;

	public Compra() {
		this.produtos = new ArrayList<Produto>();
		this.tipoPagamento = new Boleto();
		this.data = "";
		this.valor = new BigDecimal(0);
	}

	public String realizarCompra(CarrinhoDeCompras carrinho, int tipoPagamento, double desconto) {
		if(!carrinho.getProdutos().isEmpty()) {
			switch (tipoPagamento) {
			case 2:
				this.setTipoPagamento(new Paypal());
				break;
				
			case 3:
				this.setTipoPagamento(new CartaoCredito());
				break;
				
			default:
				this.setTipoPagamento(new Boleto());
				break;
			}
			produtos.addAll(carrinho.getProdutos());
			this.calculaValorDaCompra(desconto);
			this.setData(getDataExata());
			carrinho.limparCarrinho();
			carrinho.setDisponibilidade(CarrinhoDeCompras.VAZIO);
			return toString() + "\n\nForma de Pagamento" + tipoPagamento;
		}
			
		return "Compra não realizada!";
	}

	private void calculaValorDaCompra(double desconto) {
		for (Produto produto : produtos)
			this.valor.add(produto.getPreco());
		valor.multiply(new BigDecimal(this.tipoPagamento.getFatorDeAcrescimo()));
		BigDecimal valorDeDesconto = valor.multiply(new BigDecimal(desconto));
		valor = valor.subtract(valorDeDesconto);
	}

	private String getDataExata() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public void setTipoPagamento(TipoPagamento tipo) {
		this.tipoPagamento = tipo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	@Override
	public String toString() {
		String descricaoDaCompra = "Código da compra: " + codigo + "\n\nData: " + data + "\n\nItens:\n";
		for (Produto produto : produtos)
			descricaoDaCompra += produto.toStringCompra() + "\n";
		return descricaoDaCompra + "\nValor: " + valor;
	}
}