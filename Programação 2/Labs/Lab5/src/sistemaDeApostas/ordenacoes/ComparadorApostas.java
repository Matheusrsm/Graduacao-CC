package sistemaDeApostas.ordenacoes;

import java.util.Comparator;

import sistemaDeApostas.cenario.Cenario;

/**
 * Ordenador de cenários por numero de apostas.
 * Em caso de empate, ordena por ordem de cadastro, pelo ID.
 * 
 * @author Matheus Silva - 117110412
 * 
 */
public class ComparadorApostas implements Comparator<Cenario>{

	@Override
	public int compare(Cenario c1, Cenario c2) {
		if (c2.getApostasDoCenario().size() < c1.getApostasDoCenario().size()) {
			return -1;
		}
		if (c2.getApostasDoCenario().size() > c1.getApostasDoCenario().size()) {
			return 1;
		}
		if(c1.getId() > c2.getId()) {return 1;}
		if(c1.getId() < c2.getId()) {return -1;}
		return 0;
	}
}