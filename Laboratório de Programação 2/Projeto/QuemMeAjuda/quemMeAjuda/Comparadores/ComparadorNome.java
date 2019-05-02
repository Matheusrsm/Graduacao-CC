package quemMeAjuda.Comparadores;

import java.util.Comparator;

import quemMeAjuda.Entidades.Aluno.*;

/**
* Classe que implementa a interface Comparator.
* Tem como parametro de comparação o nome dos Alunos.
* 
* @author Matheus Silva, Wesley Monte
*
*/
public class ComparadorNome implements Comparator<Aluno> {

	/**
	 * Compara dois Alunos através do Nome (String).
	 */
	@Override
	public int compare(Aluno a1, Aluno a2) {
		return a1.getNome().compareTo(a2.getNome());
	}
}