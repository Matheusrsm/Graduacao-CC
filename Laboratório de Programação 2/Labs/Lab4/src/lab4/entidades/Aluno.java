package lab4.entidades;

/**
 * Representa��o de um Aluno. O aluno deve possuir uma matr�cula, um
 * nome, e seu curso.
 * 
 * @author Matheus Silva - 117110412
 */
public class Aluno {

	private String matricula, nome, curso;
	
	/**
	 * Constr�i um aluno a partir de uma matr�cula, nome e curso.
	 * 
	 * @param matricula
	 *            A matr�cula do aluno.
	 * @param nome
	 *            O nome do aluno.
	 * @param curso
	 *            O curso do aluno.
	 */
	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}

	/**
	 * M�todo get para a matr�cula do aluno.
	 * 
	 * @return o n�mero de matr�cula do aluno.
	 */
	public String getMatricula() {return matricula;}
	
	/**
	 * Gera um inteiro que representa hashCode de Aluno a partir de seu n�mero de
	 * matr�cula.
	 *
	 * @return o inteiro representando o hashCode do Aluno.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * M�todo equals, que compara o objeto com outro a partir da
	 * matr�cula.
	 * 
	 * @param obj
	 *            Objeto a ser comparado.
	 * @return um valor booleano que indica se os objetos t�m o mesmo n�mero de
	 *         matr�cula ou n�o.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	/**
	 * Retorna a representa��o em String de um aluno. A representa��o segue o
	 * formato: "Matr�cula - Nome - Curso".
	 * 
	 * @return uma string que representa o aluno.
	 */
	@Override
	public String toString() {return matricula + " - " + nome + " - " + curso;}
}