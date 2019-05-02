package lab4.entidades;

import java.util.HashMap;
import java.util.ArrayList;
import lab4.auxiliares.Excecoes;

/**
 * Representa��o de um controle de Alunos que cont�m o mapeamento do objeto
 * Aluno pela sua matr�cula, e uma lista de refer�ncias aos alunos que 
 * responderam quest�es. Tamb�m pode cadastrar, imprimir e alocar alunos,
 * registrar e imprimir alunos que responderam.
 * 
 * @author Matheus Silva - 117110412
 */
public class ControleAlunos {

	private HashMap<String, Aluno> controleAlunos = new HashMap<>();
	private ArrayList<Aluno> alunosResponderam = new ArrayList<>();
	private Excecoes excecoes = new Excecoes();
	
	/**
	 * M�todo que invoca o construtor de um aluno, e o cadastra no mapa, com sua
	 * matr�cula como chave, caso ele n�o tenha sido cadastrado ainda.
	 * 
	 * @param matricula
	 *            A Matr�cula do aluno.
	 * @param nome
	 *            O nome do aluno.
	 * @param curso
	 *            O curso do aluno.
	 * @return uma String que diz se o aluno foi cadastrado ou se a matr�cula j�
	 * havia sido cadastrada.
	 */
	public String cadastraAluno(String matricula, String nome, String curso) {
		excecoes.cadastraAlunoInvalido(matricula, nome, curso);
		if(alunoExiste(matricula)) {return "MATR�CULA J� CADASTRADA!";}
		controleAlunos.put(matricula, new Aluno(matricula, nome, curso));
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * M�todo que procura um aluno no mapa pela sua matr�cula, e retorna sua
	 * representa��o em String.
	 * 
	 * @param matricula
	 *            A matr�cula do aluno.
	 * @return a representa��o em String do aluno ou uma String dizendo que o aluno
	 * ainda n�o foi cadastrado.
	 */
	public String consultaAluno(String matricula) {
		excecoes.matriculaInvalida(matricula);
		if(alunoExiste(matricula)) {return "\nAluno: " + controleAlunos.get(matricula).toString();}
		return "Aluno n�o cadastrado.";
	}
	
	/**
	 * M�todo que retorna o Mapa do Controle de Alunos.
	 * 
	 * @return o Controle de Alunos.
	 */
	public HashMap<String, Aluno> getControleAlunos() {return controleAlunos;}
	
	/**
	 * M�todo que verifica a exist�ncia de um aluno no mapa pela sua matr�cula.
	 * 
	 * @param matricula
	 *            A matr�cula do aluno.
	 * @return um valor booleano indicando a exist�ncia do aluno.
	 */
	public boolean alunoExiste(String matricula) {
		excecoes.matriculaInvalida(matricula);
		return controleAlunos.containsKey(matricula);
	}
	
	/**
	 * Registra a resposta de um aluno, adicionando sua matr�cula numa lista como
	 * refer�ncia.
	 * 
	 * @param matricula
	 *            A matr�cula do aluno que respondeu.
	 * @return uma String que indica se o aluno foi registrado ou se ele ainda 
	 * n�o foi cadastrado.
	 */
	public String registraAlunoRespondeu(String matricula) {
		excecoes.matriculaInvalida(matricula);
		if(alunoExiste(matricula)) {
			alunosResponderam.add(controleAlunos.get(matricula));
			return "ALUNO REGISTRADO!";
		}
		return "Aluno n�o cadastrado.";
	}
	
	/**
	 * Imprime todos os alunos que responderam ordenados, com sua representa��o em
	 * String. A representa��o segue o formato: 
	 * "Alunos: 
	 * (ordem de resposta). * matr�cula - nome - curso"
	 * 
	 * @return a String dos alunos que responderam.
	 */
	public String imprimirAlunosResponderam() {
		String saida = "Alunos: \n";
		int i = 1;
		for(Aluno alunoRespondeu: alunosResponderam) {
			saida += i + ". " + alunoRespondeu.toString() + "\n";
			i++;
		}
		return saida;
	}
}