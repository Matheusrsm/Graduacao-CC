package sistemaDeApostas.aposta;

/**
 * Representação abstrata de um seguro que possui: 
 * Custo
 * Tipo
 * Identificador
 * 
 * @author Matheus Silva - 117110412
 *
 */
public abstract class Seguro {
	
	final String SEGURO_VALOR = "VALOR";
	final String SEGURO_TAXA = "TAXA";

	private int custo, identificador;
	protected String tipo;
	
	/**
	 * Constroi um Seguro com custo e identificador.
	 * @param custo 
	 * 		O custo do Seguro no formato Inteiro.
	 * @param identificador 
	 * 		Identificador númerico no formato Inteiro.
	 */
	public Seguro(int custo, int identificador) {
		this.custo = custo;
		this.identificador = identificador;
	}

	public int getCusto() {
		return custo;
	}

	public int getIdentificador() {
		return identificador;
	}
	
	public abstract double getAssegurado();
	
	@Override
	public abstract String toString();
}