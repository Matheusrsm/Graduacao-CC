package lab4.auxiliares;

/**
 * Classe de Valida��o de Entradas. Verifica entradas inv�lidas e lan�a
 * exce��es, caso sejam.
 * 
 * @author Matheus Silva - 117110412
 */
public class Excecoes {

	/**
	 * Verifica se uma matr�cula � nula ou vazia.
	 * 
	 * @param matricula
	 *            A String a ser verificada.
	 */
	public void matriculaInvalida(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("Matr�cula Nula");
		}

		if (matricula.trim().isEmpty()) {
			throw new IllegalArgumentException("Matr�cula Inv�lida");
		}
	}

	/**
	 * Verifica se o nome do Grupo � nulo ou vazio.
	 * 
	 * @param nomeDoGrupo
	 *            A String a ser verificada.
	 */
	public void nomeDoGrupoInvalido(String nomeDoGrupo) {
		if (nomeDoGrupo == null) {
			throw new NullPointerException("Nome Nulo");
		}

		if (nomeDoGrupo.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome Inv�lido");
		}
	}

	/**
	 * Verifica se os par�metros de cadastro de um Aluno são inv�lidos.
	 * 
	 * @param matricula
	 *            A matr�cula do aluno.
	 * @param nome
	 *            O nome do aluno.
	 * @param curso
	 *            O curso do aluno.
	 */
	public void cadastraAlunoInvalido(String matricula, String nome, String curso) {
		if (matricula == null) {
			throw new NullPointerException("Matr�cula Nula");
		}

		if (nome == null) {
			throw new NullPointerException("Nome Nulo");
		}

		if (curso == null) {
			throw new NullPointerException("Curso Nulo");
		}

		if (matricula.trim().isEmpty()) {
			throw new IllegalArgumentException("Matr�cula Inv�lida");
		}

		if (nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome Inv�lido");
		}

		if (curso.trim().isEmpty()) {
			throw new IllegalArgumentException("Curso Inv�lido");
		}
	}

}
