package sistemaDeApostas.aposta;

/**
 * Representação abstrata de um seguro que possui: 
 * Taxa
 * Custo
 * Identificador
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class SeguroTaxa extends Seguro{

	private double taxa;
	
	/**
	 * Constroi um Seguro por Taxa com taxa, custo e identificador.
	 * @param taxa
	 * 		A taxa do Seguro no formato Double.
	 * @param custo 
	 * 		O custo do Seguro no formato Inteiro.
	 * @param identificador 
	 * 		Identificador númerico no formato Inteiro.
	 */
	public SeguroTaxa(double taxa, int custo, int identificador) {
		super(custo, identificador);
		this.taxa = taxa;
		this.tipo = SEGURO_TAXA;
	}
	
	@Override
	public double getAssegurado() {
		return taxa;
	}

	public void setTaxaSeguro(double novaTaxa) {
		this.taxa = novaTaxa;
	}
	
	/**
	 * @return uma representação textual do Seguro no formato String.
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) - " + taxa * 100 + "%";
	}
}