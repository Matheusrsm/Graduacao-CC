package sistemaDeApostas.cenario;
/**
 * Representação de um cenário de apostas bonificado.
 * Um cenário bonificado possui:
 * Identificador.
 * Descrição do Cenário.
 * Estado de finalização ou não do Cenário.
 * Lista de apostas que foram feitas para o cenário.
 * Bônus.
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class CenarioBonus extends Cenario {

	private int bonus;
	
	/**
	 * Constroi um Cenario Bonificado com identificador, descricao e bônus.
	 * @param id
	 * 		O Identificador númerico no formato Inteiro.
	 * @param descricao
	 * 		Uma representação textual no formato String que descreve o Cenário.
	 * @param bonus
	 * 		O valor do Bônus dado ao Cenário, no formato Inteiro.
	 */
	public CenarioBonus(int id, String descricao, int bonus) {
		super(id, descricao);
		this.bonus = bonus;
	}
	
	@Override
	public int getBonus() {
		return bonus;
	}
	
	/**
	 * @return uma representação textual do Cenário Bonificado, no formato String.
	 */
	@Override
	public String toString() {
		int parteInteira = bonus / 100;
		int parteFloat = bonus % 100;
		return super.toString() + " - R$ " + parteInteira + "," + String.format("%02d", parteFloat);
	}
}