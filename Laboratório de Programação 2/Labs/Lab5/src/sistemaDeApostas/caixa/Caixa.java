package sistemaDeApostas.caixa;

/**
 * Representação da entidade organizadora do dinheiro destinado as apostas, possui:
 * Taxa
 * Valor de Caixa
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class Caixa {

	private int valorCaixa;
	private double taxa;
	
	/**
	 * Constroi o Caixa com um valor inicial de caixa e um valor de taxa.
	 * @param valorCaixa  
	 * 		Valor inicial do caixa em centavos, no formato inteiro.
	 * @param taxa  
	 * 		Valor da taxa no formato double, a taxa está entre 0 e 1. 
	 */
	public Caixa(int valorCaixa, double taxa) {
		this.valorCaixa = valorCaixa;
		this.taxa = taxa;
	}

	public int getValorCaixa() {
		return valorCaixa;
	}

	/**
	 * Incremeta um dado valor ao caixa.
	 * @param valor
	 * 		O valor a ser adicionado no Caixa, no formato Double.
	 */
	public void adicionaValorCaixa(double valor) {
		this.valorCaixa += valor;
	}
	
	/**
	 * Decremeta um dado valor do caixa.
	 * @param valor
	 * 		O valor a ser retirado do Caixa, no formato Double.
	 */
	public void retiraValorCaixa(double valor) {
		this.valorCaixa -= valor;
	}

	public double getTaxa() {
		return taxa;
	}
}