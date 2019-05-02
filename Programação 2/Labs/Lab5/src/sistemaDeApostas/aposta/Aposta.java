package sistemaDeApostas.aposta;

/**
 * Representação de uma aposta que possui:
 * Nome do apostador
 * Previsao do apostador
 * Valor da aposta
 * Seguro ou não possui Seguro
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class Aposta {

	private String nomeApostador, previsao;
	private int valorAposta;
	private Seguro seguro;
	
	/**
	 * Constroi um Aposta. 
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param valorAposta 
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * Esta Aposta nao possui seguro.
	 */
	public Aposta(String nomeApostador, String previsao, int valorAposta) {
		this.nomeApostador = nomeApostador;
		this.previsao = previsao;
		this.valorAposta = valorAposta;
		this.seguro = null;
	}
	
	/**
	 * Constroi um Aposta com Seguro por Valor. 
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param valorAposta 
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param valorSeguro
	 * 		Valor do Seguro, no formato Inteiro.
	 * @param custo
	 * 		Custo para assegurar a Aposta, no formato Inteiro.
	 * @param identificador
	 * 		Identificador da Aposta, no formato Inteiro.
	 */
	public Aposta(String nomeApostador, String previsao, int valorAposta, int valorSeguro, int custo, int identificador) {
		this(nomeApostador, previsao, valorAposta);
		this.seguro = new SeguroValor(valorSeguro, custo, identificador);
	}
	
	/**
	 * Constroi um Aposta com Seguro por Taxa. 
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param valorAposta 
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param taxaSeguro
	 * 		Taxa do Seguro, no formato Double.
	 * @param custo
	 * 		Custo para assegurar a Aposta, no formato Inteiro.
	 * @param identificador
	 * 		Identificador da Aposta, no formato Inteiro.
	 */
	public Aposta(String nomeApostador, String previsao, int valorAposta, double taxaSeguro, int custo, int identificador) {
		this(nomeApostador, previsao, valorAposta);
		this.seguro = new SeguroTaxa(taxaSeguro, custo, identificador);
	}

	public String getNomeApostador() {
		return nomeApostador;
	}

	public String getPrevisao() {
		return previsao;
	}

	public int getValorAposta() {
		return valorAposta;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}
	
	public void setSeguro(Seguro novoSeguro) {
		this.seguro = novoSeguro;
	}

	/**
	 * @return para Apostas Não Asseguradas
	 * 		uma representação textual da aposta no formato String "nomeApostador - R$valorAposta - previsao".
	 * @return para Apostas Asseguradas
	 * 		uma representação textual da aposta no formato String "nomeApostador - R$valorAposta - previsao - representacao textual do Seguro".
	 */
	@Override
	public String toString() {
		if(getSeguro() != null) {
			return nomeApostador + " - R$" + String.format("%.2f", (double)(valorAposta/100)) + " - " + previsao + seguro.toString();
		}
		return nomeApostador + " - R$" + String.format("%.2f", (double)(valorAposta/100)) + " - " + previsao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeApostador == null) ? 0 : nomeApostador.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + valorAposta;
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
		Aposta other = (Aposta) obj;
		if (nomeApostador == null) {
			if (other.nomeApostador != null)
				return false;
		} else if (!nomeApostador.equals(other.nomeApostador))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (valorAposta != other.valorAposta)
			return false;
		return true;
	}
}