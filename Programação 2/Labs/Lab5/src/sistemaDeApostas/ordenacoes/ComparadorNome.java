package sistemaDeApostas.ordenacoes;

import java.util.Comparator;

import sistemaDeApostas.cenario.Cenario;

/**
 * Ordenador de cenários por ordem Alfabética
 * 
 * @author Matheus Silva - 117110412
 * 
 */
public class ComparadorNome implements Comparator<Cenario>{
	
	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getDescricao().compareTo(c2.getDescricao());
	}
}
