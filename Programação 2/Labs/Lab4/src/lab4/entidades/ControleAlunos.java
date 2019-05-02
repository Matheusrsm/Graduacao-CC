package lab4.entidades;

import java.util.HashMap;
import java.util.ArrayList;
import lab4.auxiliares.Excecoes;

/**
 * Representação de um controle de Alunos que contém o mapeamento do objeto
 * Aluno pela sua matrícula, e uma lista de referências aos alunos que 
 * responderam questões. Também pode cadastrar, imprimir e alocar alunos,
 * registrar e imprimir alunos que responderam.
 * 
 * @author Matheus Silva - 117110412
 */
public class ControleAlunos {

	private HashMap<String, Aluno> controleAlunos = new HashMap<>();
	private ArrayList<Aluno> alunosResponderam = new ArrayList<>();
	private Excecoes excecoes = new Excecoes();
	
	/**
	 * Método que invoca o construtor de um aluno, e o cadastra no mapa, com sua
	 * matrícula como chave, caso ele não tenha sido cadastrado ainda.
	 * 
	 * @param matricula
	 *            A Matrícula do aluno.
	 * @param nome
	 *            O nome do aluno.
	 * @param curso
	 *            O curso do aluno.
	 * @return uma String que diz se o aluno foi cadastrado ou se a matrícula já
	 * havia sido cadastrada.
	 */
	public String cadastraAluno(String matricula, String nome, String curso) {
		excecoes.cadastraAlunoInvalido(matricula, nome, curso);
		if(alunoExiste(matricula)) {return "MATRÍCULA JÁ CADASTRADA!";}
		controleAlunos.put(matricula, new Aluno(matricula, nome, curso));
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * Método que procura um aluno no mapa pela sua matrícula, e retorna sua
	 * representação em String.
	 * 
	 * @param matricula
	 *            A matrícula do aluno.
	 * @return a representação em String do aluno ou uma String dizendo que o aluno
	 * ainda não foi cadastrado.
	 */
	public String consultaAluno(String matricula) {
		excecoes.matriculaInvalida(matricula);
		if(alunoExiste(matricula)) {return "\nAluno: " + controleAlunos.get(matricula).toString();}
		return "Aluno não cadastrado.";
	}
	
	/**
	 * Método que retorna o Mapa do Controle de Alunos.
	 * 
	 * @return o Controle de Alunos.
	 */
	public HashMap<String, Aluno> getControleAlunos() {return controleAlunos;}
	
	/**
	 * Método que verifica a existência de um aluno no mapa pela sua matrícula.
	 * 
	 * @param matricula
	 *            A matrícula do aluno.
	 * @return um valor booleano indicando a existência do aluno.
	 */
	public boolean alunoExiste(String matricula) {
		excecoes.matriculaInvalida(matricula);
		return controleAlunos.containsKey(matricula);
	}
	
	/**
	 * Registra a resposta de um aluno, adicionando sua matrícula numa lista como
	 * referência.
	 * 
	 * @param matricula
	 *            A matrícula do aluno que respondeu.
	 * @return uma String que indica se o aluno foi registrado ou se ele ainda 
	 * não foi cadastrado.
	 */
	public String registraAlunoRespondeu(String matricula) {
		excecoes.matriculaInvalida(matricula);
		if(alunoExiste(matricula)) {
			alunosResponderam.add(controleAlunos.get(matricula));
			return "ALUNO REGISTRADO!";
		}
		return "Aluno não cadastrado.";
	}
	
	/**
	 * Imprime todos os alunos que responderam ordenados, com sua representação em
	 * String. A representação segue o formato: 
	 * "Alunos: 
	 * (ordem de resposta). * matrícula - nome - curso"
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