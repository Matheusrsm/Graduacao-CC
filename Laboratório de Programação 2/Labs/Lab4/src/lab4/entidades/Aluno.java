package lab4.entidades;

/**
 * Representação de um Aluno. O aluno deve possuir uma matrícula, um
 * nome, e seu curso.
 * 
 * @author Matheus Silva - 117110412
 */
public class Aluno {

	private String matricula, nome, curso;
	
	/**
	 * Constrói um aluno a partir de uma matrícula, nome e curso.
	 * 
	 * @param matricula
	 *            A matrícula do aluno.
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
	 * Método get para a matrícula do aluno.
	 * 
	 * @return o número de matrícula do aluno.
	 */
	public String getMatricula() {return matricula;}
	
	/**
	 * Gera um inteiro que representa hashCode de Aluno a partir de seu número de
	 * matrícula.
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
	 * Método equals, que compara o objeto com outro a partir da
	 * matrícula.
	 * 
	 * @param obj
	 *            Objeto a ser comparado.
	 * @return um valor booleano que indica se os objetos têm o mesmo número de
	 *         matrícula ou não.
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
	 * Retorna a representação em String de um aluno. A representação segue o
	 * formato: "Matrícula - Nome - Curso".
	 * 
	 * @return uma string que representa o aluno.
	 */
	@Override
	public String toString() {return matricula + " - " + nome + " - " + curso;}
}