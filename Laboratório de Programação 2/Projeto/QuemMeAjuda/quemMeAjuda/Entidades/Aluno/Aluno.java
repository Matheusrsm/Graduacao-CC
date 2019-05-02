package quemMeAjuda.Entidades.Aluno;

import java.io.Serializable;

/**
 * Classe que representa um Aluno.
 * Um Aluno tem:
 * Identificador
 * Nome
 * Matricula
 * Codigo do Curso
 * Email
 * Telefone (Opcional)
 * Nota de Avaliacao (Inicia com 5.0)
 * Tutoria (caso o Aluno queira se tornar um tutor)
 * 
 * A Classe ainda implementa a Interface Comparable para comparar dois Alunos, a fim de ordena-los.
 * 
 * @author Matheus Silva, Wesley Monte, Lukas Soares
 *
 */
public class Aluno implements Comparable<Aluno>, Serializable {

	private static final long serialVersionUID = 5632534491723591604L;
	private String  matricula, nome, email, telefone;
	private int     codigoCurso, iD;
	private double  notaDeAvaliacao;
	private Tutoria tutoria;
	
	/**
	 * Constroi um Aluno a partir do ID, matricula, codigo do curso, telefone e email 
	 * @param iD inteiro identificador do Aluno.
	 * @param nome nome do aluno.
	 * @param matricula matricula do aluno.
	 * @param codigoCurso codigo de curso do aluno.
	 * @param telefone telefone do aluno.
	 * @param email email do aluno.
	 */
	public Aluno(int iD, String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.iD = iD;
		this.nome = nome;
		this.matricula = matricula;
		this.codigoCurso = codigoCurso;
		this.email = email;
		this.telefone = telefone;
		this.notaDeAvaliacao = 5.0;
	}
	
	/**
	 * Método que torna o Aluno um tutor, ou seja, o atributo Tutoria agora passar a nao ser null.
	 * Ainda adiciona a disciplina a tutoria do Aluno.
	 * 
	 * @param disciplina disciplina que o Aluno quer virar tutor.
	 * @param proficiencia proficiencia do Aluno na disciplina que ira ser tutor.
	 */
	public void tornaAlunoTutor(String disciplina, int proficiencia) {
		tutoria = new Tutoria();
		tutoria.adicionarDisciplina(disciplina, proficiencia);
	}
	
	/**
	 * @return boolean que indica se o Aluno eh um tutor, ou seja, se seu atributo tutoria eh null ou nao.
	 */
	public boolean isTutor() {
		if (tutoria == null)
			return false;
		return true;
	}
	
	public String getMatricula() {return matricula;}

	public String getNome() {return nome;}

	public String getEmail() {return email;}

	public String getTelefone() {return telefone;}

	public int getCodigoCurso() {return codigoCurso;}
	
	public Tutoria getTutoria() {return tutoria;}

	public double getNotaDeAvaliacao() {return notaDeAvaliacao;}
	
	public void setNotaDeAvaliacao(double novaNota) {this.notaDeAvaliacao = novaNota;}
	
	public int getID() {return iD;}
	
	@Override
	public String toString() {
		if (telefone.trim().isEmpty()) return matricula + " - " + nome + " - " + codigoCurso + " - " +  email;
		return matricula + " - " + nome + " - " + codigoCurso + " - " + telefone + " - " +  email;
	}
	
	@Override
	public int compareTo(Aluno outroAluno) {return getNome().compareTo(outroAluno.getNome());}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

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
}