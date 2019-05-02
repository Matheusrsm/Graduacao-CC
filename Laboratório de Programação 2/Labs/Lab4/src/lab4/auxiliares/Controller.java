package lab4.auxiliares;

import lab4.entidades.ControleAlunos;
import lab4.entidades.ControleGrupos;

/**
 * Representação de um Controller que gerencia os Controles de Grupo e Aluno
 * e releciona-se com a classe Sistema, a interface com o usuário.
 *  
 * @author Matheus Silva - 117110412
 */
public class Controller {

	private Menu input = new Menu();
	private ControleAlunos alunos = new ControleAlunos();
	private ControleGrupos grupos = new ControleGrupos();
	
	/**
	 * Método que invoca o Menu e faz a leitura da operação desejada
	 * pelo usuário.
	 * 
	 * @return uma String que representa a operação.
	 */
	public String leituraOperacao() {
		return input.leOperacao();
	}
	
	/**
	 * Método que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usuário e realiza ou não a operação de Cadastro de um Aluno.
	 */
	public void cadastrarAluno() {
		String matricula = input.leString("Matrícula: ");
		String nome = input.leString("Nome: ");
		String curso = input.leString("Curso: ");
		System.out.println(alunos.cadastraAluno(matricula, nome, curso));
	}
	
	/**
	 * Método que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usuário e realiza ou não a operação de Consulta de um Aluno.
	 */
	public void consultarAluno() {
		String matricula_procurada = input.leString("Matrícula: ");
		System.out.println(alunos.consultaAluno(matricula_procurada));
	}
	
	/**
	 * Método que invoca o Menu e o Controle de Grupos, faz a leitura dos dados
	 * do usuário e realiza ou não a operação de Cadastro de um Grupo.
	 */
	public void cadastrarGrupo() {
		String nome_grupo = input.leString("Grupo: ");
		System.out.println(grupos.cadastraGrupo(nome_grupo));
	}
	
	/**
	 * Método que invoca o Menu e o Controle de Grupos, faz a leitura dos dados
	 * do usuário e realiza ou não duas operações:
	 * Alocar um Aluno em um Grupo ou Imprimir um Grupo.
	 */
	public void alocarOuImprimir() {
		String opcao = input.leString("(A)locar Aluno ou (I)mprimir Grupo? ");
		if(opcao.equals("A")) {
			String matriculaAluno = input.leString("Matrícula: ");
			String nomeGrupo = input.leString("Grupo: ");
			System.out.println(grupos.alocaAlunoNoGrupo(nomeGrupo, matriculaAluno, alunos));
		}
		else {
			String nomeGrupo = input.leString("Grupo: ");
			System.out.println(grupos.imprimeGrupo(nomeGrupo));
		}
	}
	
	/**
	 * Método que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usuário e realiza ou não a operação de Registro de um Aluno que respondeu.
	 */
	public void registrar() {
		String matriculaAlunoRespondeu = input.leString("Matrícula: ");
		System.out.println(alunos.registraAlunoRespondeu(matriculaAlunoRespondeu));
	}
	
	/**
	 * Método que invoca o Controle de Alunos e realiza ou não a operação de Imprimir
	 * os Alunos que responderam.
	 */
	public void imprimir() {
		System.out.println(alunos.imprimirAlunosResponderam());
	}
}