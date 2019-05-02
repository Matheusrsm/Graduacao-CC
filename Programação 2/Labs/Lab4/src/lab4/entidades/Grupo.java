package lab4.entidades;

import java.util.ArrayList;

/**
 * Representa��o de um Grupo de estudos. O grupo deve possuir um nome, e uma lista
 * com os alunos que est�o inclu�dos nele, sendo poss�vel alocar um aluno ao
 * grupo.
 * 
 * @author Matheus Silva - 117110412
 */
public class Grupo {
	
	private String nome;
	private ArrayList<Aluno> alunosDoGrupo;
	
	/**
	 * Constr�i um Grupo a partir de seu nome.
	 * 
	 * @param nome
	 *            O nome do grupo a ser criado.
	 */
	public Grupo(String nome) {
		this.nome = nome;
		this.alunosDoGrupo = new ArrayList<>();
	}

	/**
	 * Verifica se o aluno j� foi alocado no Grupo.
	 * 
	 * @param matricula
	 *            A matr�cula do Aluno para a verifica��o.
	 *
	 * @return um booleano indicando se o aluno j� foi alocado ou n�o no Grupo.
	 */
	private boolean alunoExisteNoGrupo(String matricula) {
		for(Aluno aluno: alunosDoGrupo) {
			return aluno.getMatricula().equals(matricula);
		}
		return false;
	}
	
	/**
	 * Aloca um aluno ao grupo, adicionando-o a sua lista de alunos.
	 * 
	 * @param matricula
	 *            A matr�cula do Aluno a ser adicionado no grupo ou n�o.
	 * @param alunos
	 * 			  O controle de Alunos para verificar se o aluno foi cadastrado.
	 * @return uma string indentificando que o aluno foi alocado no grupo ou se ele
	 * n�o foi cadastrado no controle de Alunos.
	 */
	public String alocaAluno(String matricula, ControleAlunos alunos) {
		if(!alunoExisteNoGrupo(matricula)) {
			if(alunos.alunoExiste(matricula)) {
				alunosDoGrupo.add(alunos.getControleAlunos().get(matricula));
				return "ALUNO ALOCADO!";
			}
			return "Aluno n�o cadastrado.";
		}
		return "ALUNO ALOCADO!";
	}
	
	/**
	 * M�todo get para o nome do Grupo.
	 * 
	 * @return o nome do grupo.
	 */
	public String getNome() {return nome;}

	/**
	 * Retorna a representa��o em String de um grupo. A representa��o segue o
	 * formato: 
	 * "Alunos do Grupo (nome): 
	 * * Representa��o em String de cada aluno".
	 * 
	 * @return uma String que representa o grupo.
	 */
	public String imprimirGrupo() {
		String saida = "Alunos do grupo " + nome + ":\n";
		for(Aluno aluno: alunosDoGrupo) {
			saida += "* " + aluno.toString() + "\n";
		}
		return saida;
	}

	/**
	 * Gera um inteiro que representa hashCode do Grupo a partir de seu nome.
	 *
	 * @return o inteiro representando o hashCode do Grupo.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome.toLowerCase() == null) ? 0 : nome.toLowerCase().hashCode());
		return result;
	}

	/**
	 * M�todo equals, que compara o objeto com outro a partir de seu nome.
	 * 
	 * @param obj
	 *            Objeto a ser comparado.
	 * @return um valor booleano que indica se os objetos t�m o mesmo nome ou n�o.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (nome.toLowerCase() == null) {
			if (other.nome.toLowerCase() != null)
				return false;
		} else if (!nome.toLowerCase().equals(other.nome.toLowerCase()))
			return false;
		return true;
	}
}