package sistemaDeApostas.aposta;

/**
 * Representação abstrata de um seguro que possui: 
 * Valor
 * Custo
 * Identificador
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class SeguroValor extends Seguro{

	private int valor;
	
	/**
	 * Constroi um Seguro por Valor com valor, custo e identificador.
	 * @param valor
	 * 		O valor do Seguro no formato Inteiro.
	 * @param custo 
	 * 		O custo do Seguro no formato Inteiro.
	 * @param identificador 
	 * 		Identificador númerico no formato Inteiro.
	 */
	public SeguroValor(int valor, int custo, int identificador) {
		super(custo, identificador);
		this.valor = valor;
		this.tipo = SEGURO_VALOR;
	}
	
	@Override
	public double getAssegurado() {
		return (int) valor;
	}
	
	public void setValorSeguro(int novoValor) {
		this.valor = novoValor;
	}
	
	/**
	 * @return uma representação textual do Seguro no formato String.
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA (VALOR) - R$" + valor + ",00";
	}
}