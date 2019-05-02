package sistemaDeApostas.cenario;

import java.util.ArrayList;
import sistemaDeApostas.aposta.Aposta;
import sistemaDeApostas.aposta.SeguroTaxa;
import sistemaDeApostas.caixa.Caixa;

/**
 * Representação de um cenário de apostas.
 * Um cenário possui:
 * Identificador.
 * Descrição do Cenário.
 * Estado de finalização ou não do Cenário.
 * Lista de apostas que foram feitas para o cenário.
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class Cenario implements Comparable<Cenario>{

	private String descricao, estado;
	private int id;
	private ArrayList<Aposta> apostasDoCenario;
	
	/**
	 * Constroi um cenário com uma descrição e um identificador.
	 * @param descricao 
	 * 		Uma representação textual no formato String que descreve o Cenário.
	 * @param id 
	 * 		O identificador númerico do Cenário no formato Inteiro.
	 */
	public Cenario(int id, String descricao) {
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.id = id;
		this.apostasDoCenario = new ArrayList<Aposta>();
	}

	public int getBonus() {return 0;}
	
	public String getDescricao() {return descricao;}

	public String getEstado() {return estado;}
	
	public int getId() {return id;}

	public ArrayList<Aposta> getApostasDoCenario() {return apostasDoCenario;}

	public void setEstado(String estado) {this.estado = estado;}
	
	/**
	 * Cadastra uma Aposta no Cenário, ou seja, adiciona uma nova Aposta na Lista de Apostas.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 */
	public void cadastroAposta(String nomeApostador, int valor, String previsao) {
		apostasDoCenario.add(new Aposta(nomeApostador, previsao, valor));
	}
	
	/**
	 * Cadastra uma Aposta Assegurada por Valor no Cenário, ou seja, adiciona uma nova Aposta na Lista de Apostas.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param valorSeguro
	 * 		Valor do Seguro da Aposta, no formato Inteiro.
	 * @param custo
	 * 		O custo de se ter um Seguro em uma Aposta, no formato Inteiro.
	 * @return o identificador da Aposta Assegurada.
	 */
	public int cadastroApostaSeguroValor(String nomeApostador, int valor, String previsao, int valorSeguro, int custo) {
		int idApostaAssegurada = apostasDoCenario.size();
		apostasDoCenario.add(new Aposta(nomeApostador, previsao, valor, valorSeguro, custo, idApostaAssegurada));
		return idApostaAssegurada;
	}
	
	/**
	 * Cadastra uma Aposta Assegurada por Taxa no Cenário, ou seja, adiciona uma nova Aposta na Lista de Apostas.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param taxaSeguro
	 * 		Taxa do Seguro da Aposta, no formato Double.
	 * @param custo
	 * 		O custo de se ter um Seguro em uma Aposta, no formato Inteiro.
	 * @return o identificador da Aposta Assegurada.
	 */
	public int cadastroApostaSeguroTaxa(String nomeApostador, String previsao, int valor, double taxaSeguro, int custo) {
		int idApostaAssegurada = apostasDoCenario.size();
		apostasDoCenario.add(new Aposta(nomeApostador, previsao, valor, taxaSeguro, custo, idApostaAssegurada));
		return idApostaAssegurada;
	}
	
	/**
	 * Método que verifica se uma Aposta foi declarada como Perdedora através da análise da Previsão dada pelo Apostador
	 * e do estado do Cenário.
	 * @param aposta
	 * 		A Aposta utilizada para a verificação descrita acima.
	 * @return um boolean que indica se a Aposta foi declarada perdedora ou não.
	 */
	private boolean apostaPerdedora(Aposta aposta) {
		if(aposta.getPrevisao().equals("VAI ACONTECER") && estado.equals("Finalizado (n ocorreu)")) return true;
		else if(aposta.getPrevisao().equals("N VAI ACONTECER") && estado.equals("Finalizado (ocorreu)")) return true;
		return false;
	}
	
	/**
	 * Método que calcula o pagamento do Seguro da Aposta Assegurada e realiza as operações no Caixa.
	 * @param caixa
	 * 		O caixa que irá realizar as ações de adicionar valor ao caixa ou retirar.
	 */
	public void pagamentoSeguro(Caixa caixa) {
		for(Aposta aposta: apostasDoCenario) {
			if(apostaPerdedora(aposta) && aposta.getSeguro() != null) {
				caixa.adicionaValorCaixa(caixa.getTaxa() * aposta.getValorAposta());
				if(aposta.getSeguro() instanceof SeguroTaxa) {
					caixa.retiraValorCaixa(aposta.getSeguro().getAssegurado() * aposta.getValorAposta());
				}
				else caixa.retiraValorCaixa(aposta.getSeguro().getAssegurado());
			}
		}
	}
	
	/**
	 * Método que calcula o valor total das Apostas declaradas Perdedoras no Cenário.
	 * @return o valor total das Apostas declaradas Perdedoras.
	 */
	public int valorPerdedores() {
		int valor = 0;
		for(Aposta aposta: apostasDoCenario) {
			if(apostaPerdedora(aposta)) {
				valor += aposta.getValorAposta();
			}
		}
		return valor;
	}
	
	/**
	 * @return a representação textual do Cenário, no formato String.
	 */
	@Override
	public String toString() {
		return id + " - " + descricao + " - " + estado;
	}
	
	/**
	 * @return a representação textual de todas as Apostas do Cenário.
	 */
	
	public String toStringApostas() {
		String saida = "";
		for(Aposta aposta: apostasDoCenario) {
			saida += "\n" + aposta.toString();
		}
		return saida;
	}
	
	@Override
	public int compareTo(Cenario outroCenario) {
		return getDescricao().compareTo(outroCenario.getDescricao());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Cenario other = (Cenario) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
}